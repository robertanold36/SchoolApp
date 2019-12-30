package com.school.datasource;

public class Course {
    private String course_name;
    private String course_code;
    private String course_credit;
    private String course_semester;
    private String course_year;
    private String course_programme;
    private String course_category;

    public Course()
    {
        this.course_name = course_name;
        this.course_code = course_code;
        this.course_credit = course_credit;
        this.course_semester = course_semester;
        this.course_year = course_year;
        this.course_programme = course_programme;
        this.course_category = course_category;
    }


    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public String getCourse_code() {
        return course_code;
    }

    public void setCourse_code(String course_code) {
        this.course_code = course_code;
    }

    public String getCourse_credit() {
        return course_credit;
    }

    public void setCourse_credit(String course_credit) {
        this.course_credit = course_credit;
    }

    public String getCourse_semester() {
        return course_semester;
    }

    public void setCourse_semester(String course_semester) {
        this.course_semester = course_semester;
    }

    public String getCourse_year() {
        return course_year;
    }

    public void setCourse_year(String course_year) {
        this.course_year = course_year;
    }

    public String getCourse_programme() {
        return course_programme;
    }

    public void setCourse_programme(String course_programme) {
        this.course_programme = course_programme;
    }

    public String getCourse_category()
    {
        return course_category;
    }

    public void setCourse_category(String course_category) {
        this.course_category = course_category;
    }
}
