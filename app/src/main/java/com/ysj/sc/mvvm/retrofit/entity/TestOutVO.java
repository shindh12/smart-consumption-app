package com.ysj.sc.mvvm.retrofit.entity;

public class TestOutVO {
    private String userId;
    private String firstName;
    private String lastName;
    private String registrationDate;
    private String galaxyMembership;
    private String phuLongMembership;
    private String accountNo;
    private String phone;
    private String birthDate;
    private boolean isBirthDayTarget;

    public TestOutVO(String userId, String firstName, String lastName, String registrationDate, String galaxyMembership, String phuLongMembership, String accountNo, String phone, String birthDate, boolean isBirthDayTarget) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.registrationDate = registrationDate;
        this.galaxyMembership = galaxyMembership;
        this.phuLongMembership = phuLongMembership;
        this.accountNo = accountNo;
        this.phone = phone;
        this.birthDate = birthDate;
        this.isBirthDayTarget = isBirthDayTarget;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getGalaxyMembership() {
        return galaxyMembership;
    }

    public void setGalaxyMembership(String galaxyMembership) {
        this.galaxyMembership = galaxyMembership;
    }

    public String getPhuLongMembership() {
        return phuLongMembership;
    }

    public void setPhuLongMembership(String phuLongMembership) {
        this.phuLongMembership = phuLongMembership;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public boolean isBirthDayTarget() {
        return isBirthDayTarget;
    }

    public void setBirthDayTarget(boolean birthDayTarget) {
        isBirthDayTarget = birthDayTarget;
    }
}
