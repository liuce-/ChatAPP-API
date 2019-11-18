package edu.rice.comp504.cmd;

import edu.rice.comp504.Dispatcher;
import edu.rice.comp504.model.ChatRoom;
import edu.rice.comp504.model.User;
import edu.rice.comp504.payload.SendChattingMsg;
import edu.rice.comp504.payload.response.SendChattingMsgResponse;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;

public class SendMessageCmd extends AbstractCmd {
    private HashSet<String> receivers;
    private SendChattingMsg payload;

    public SendMessageCmd(SendChattingMsg payload) {
        this.payload = payload;
        receivers = new HashSet<String>();
        receivers.addAll(Arrays.asList(payload.getReceiver()));
    }


    @Override
    public void execute(User user) {

        if (receivers.contains(user.getUsername())) {
            ChatRoom chatRoom = Dispatcher.getOnly().chatRoomMap.get(payload.getRoomID());
            SendChattingMsgResponse response = new SendChattingMsgResponse(
                payload.getSender(),
                user.getUsername(),
                chatRoom.getName(),
                payload.getRoomID(),
                chatRoom.getOwner().getUsername(),
                payload.getMsg());
            try {
                user.getSession().getRemote().sendString(response.getJsonRepresentation(gson));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
