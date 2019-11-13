package edu.rice.comp504.model;

import java.awt.*;
import java.util.HashSet;

public class Restriction {

    Point ageRestriction;  // [x, y], both inclusive
    HashSet<String> locRestriction;
    HashSet<String> schoolRestriction;

    private boolean isAgeQualified(int age) {
        return ageRestriction == null || age >= ageRestriction.x && age <= ageRestriction.y;
    }

    private boolean isLocQualified(String loc) {
        return locRestriction == null || locRestriction.contains(loc);
    }

    private boolean isSchoolQualified(String school) {
        return schoolRestriction == null || schoolRestriction.contains(school);
    }

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

    public Point getAgeRestriction() {
        return ageRestriction;
    }

    public void setAgeRestriction(Point ageRestriction) {
        this.ageRestriction = ageRestriction;
    }

    public HashSet<String> getLocRestriction() {
        return locRestriction;
    }

    public void setLocRestriction(HashSet<String> locRestriction) {
        this.locRestriction = locRestriction;
    }

    public HashSet<String> getSchoolRestriction() {
        return schoolRestriction;
    }

    public void setSchoolRestriction(HashSet<String> schoolRestriction) {
        this.schoolRestriction = schoolRestriction;
    }

    public boolean isQualified(User user) {
        return isAgeQualified(user.getAge()) && isLocQualified(user.getLocation()) && isSchoolQualified(user.getSchool());

    }
}
