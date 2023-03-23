package co.edu.unal.software_engineering.labs.pojo;

import co.edu.unal.software_engineering.labs.model.Association;
import co.edu.unal.software_engineering.labs.model.Course;

public class EnrolledCoursePOJO {

    private final Integer courseId;
    private final String courseName;
    private final Integer courseDurationHours;
    private final RolePOJO role;


    public EnrolledCoursePOJO(Association association) {
        Course course = association.getCourse();
        courseId = course.getId();
        courseName = course.getCourseName();
        courseDurationHours = course.getDurationHours();
        role = new RolePOJO(association.getRole());
    }


    public Integer getCourseId() {
        return courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public Integer getCourseDurationHours() {
        return courseDurationHours;
    }

    public RolePOJO getRole() {
        return role;
    }
}
