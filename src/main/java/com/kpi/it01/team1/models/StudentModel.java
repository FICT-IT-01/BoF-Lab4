package com.kpi.it01.team1.models;

public class StudentModel {
    private final String firstName;
    private final String lastName;
    private final String bookNumber;
    private final String phoneNumber;
    private final float averageMark;

    public StudentModel(String firstName, String lastName, String bookNumber, String phoneNumber, float averageMark) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.bookNumber = bookNumber;
        this.phoneNumber = phoneNumber;
        this.averageMark = averageMark;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getBookNumber() {
        return bookNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public float getAverageMark() {
        return averageMark;
    }
}