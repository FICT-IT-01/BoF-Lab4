package com.kpi.it01.team1.models;

public class Student {
    private String firstname;
    private String lastname;
    private String studentBookNumber;
    private String phoneNumber;
    private float averageMark;

    public Student(String firstname, String lastname, String studentBookNumber, String phoneNumber, float averageMark) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.studentBookNumber = studentBookNumber;
        this.phoneNumber = phoneNumber;
        this.averageMark = averageMark;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getStudentBookNumber() {
        return studentBookNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public float getAverageMark() {
        return averageMark;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Student student = (Student) o;
        if (!firstname.equals(student.firstname)) return false;
        if (!lastname.equals(student.lastname)) return false;
        return studentBookNumber.equals(student.studentBookNumber);
    }

    @Override
    public int hashCode() {
        int result = 31 * firstname.hashCode();
        result = 31 * result + lastname.hashCode();
        result = 31 * result + studentBookNumber.hashCode();
        return result;
    }
}