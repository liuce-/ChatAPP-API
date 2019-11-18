package edu.rice.comp504.model;

import java.awt.*;
import java.util.HashSet;

/**
 * Restrictions for a chat room.
 */
public class Restriction {

    Point ageRestriction;  // [x, y], both inclusive
    HashSet<String> locRestriction;
    HashSet<String> schoolRestriction;

    /**
     * constructor. If any of these restrictions is null, that means no restriction imposed on such area.
     *
     * @param ageRestriction age restriction, [x, y], both inclusive
     * @param locRestriction only locations in this hashset are allowed.
     * @param schoolRestriction only schools in this hashset are allowed.
     */
    public Restriction(Point ageRestriction, HashSet<String> locRestriction, HashSet<String> schoolRestriction) {
        this.ageRestriction = ageRestriction;
        this.locRestriction = locRestriction;
        this.schoolRestriction = schoolRestriction;
    }

    /**
     * check if the age is qualified.
     * @param age verify this age.
     * @return if qualified.
     */
    private boolean isAgeQualified(int age) {
        return ageRestriction == null || age >= ageRestriction.x && age <= ageRestriction.y;
    }

    /**
     * check if the location is qualified.
     * @param loc check this location.
     * @return if qualified.
     */
    private boolean isLocQualified(String loc) {
        return locRestriction == null || locRestriction.contains(loc);
    }

    /**
     * check if the school is qualified.
     * @param school check this school.
     * @return if qualified.
     */
    private boolean isSchoolQualified(String school) {
        return schoolRestriction == null || schoolRestriction.contains(school);
    }

    /**
     * getter.
     * @return ageRestriction.
     */
    public Point getAgeRestriction() {
        return ageRestriction;
    }

    /**
     * setter.
     * @param ageRestriction set this new restriction.
     */
    public void setAgeRestriction(Point ageRestriction) {
        this.ageRestriction = ageRestriction;
    }

    /**
     * getter.
     * @return get location restriction.
     */
    public HashSet<String> getLocRestriction() {
        return locRestriction;
    }

    /**
     * setter.
     * @param locRestriction set this new restriction.
     */
    public void setLocRestriction(HashSet<String> locRestriction) {
        this.locRestriction = locRestriction;
    }

    /**
     * getter.
     * @return school restriction.
     */
    public HashSet<String> getSchoolRestriction() {
        return schoolRestriction;
    }

    /**
     * setter.
     * @param schoolRestriction set this new restriction.
     */
    public void setSchoolRestriction(HashSet<String> schoolRestriction) {
        this.schoolRestriction = schoolRestriction;
    }

    /**
     * check if the user is qualified to this restriction.
     * @param user check this user.
     * @return if the user is qualified.
     */
    public boolean isQualified(User user) {
        return isAgeQualified(user.getAge()) && isLocQualified(user.getLocation()) && isSchoolQualified(user.getSchool());

    }
}
