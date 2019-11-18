package edu.rice.comp504.payload.request;

/**
 * payload of register endpoint.
 */
public class UserRegister {
    private String userName;
    private int age;
    private String school;
    private String location;

    /**
     * constructor.
     *
     * @param userName new username.
     * @param age      age.
     * @param school   school.
     * @param location location.
     */
    public UserRegister(String userName, int age, String school, String location) {
        this.userName = userName;
        this.age = age;
        this.school = school;
        this.location = location;
    }

    /**
     * getter.
     *
     * @return username
     */
    public String getUserName() {
        return userName;
    }

    /**
     * getter.
     *
     * @return age
     */
    public int getAge() {
        return age;
    }

    /**
     * getter.
     *
     * @return school
     */
    public String getSchool() {
        return school;
    }

    /**
     * getter.
     *
     * @return location.
     */
    public String getLocation() {
        return location;
    }

    /**
     * setter.
     *
     * @param location new location.
     */
    public void setLocation(String location) {
        this.location = location;
    }

}
