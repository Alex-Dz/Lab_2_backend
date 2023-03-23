package co.edu.unal.software_engineering.labs.service;

import co.edu.unal.software_engineering.labs.model.Course;
import co.edu.unal.software_engineering.labs.pojo.CoursePOJO;
import co.edu.unal.software_engineering.labs.repository.CourseRepository;
import org.springframework.stereotype.Service;


@Service
public class CourseService {

    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository, AssociationService associationService) {
        this.courseRepository = courseRepository;
    }

    public Course findById(Integer id) {
        return courseRepository.findById(id).orElse(null);
    }

    public Course save(Course course) {
        return courseRepository.save(course);
    }

    public boolean isRightCourse(Course course) {
        return course.getId() == null && !course.getCourseName().trim().isEmpty() &&
                course.getDurationHours() != null;
    }

    public Course mapperCourseEntity(CoursePOJO coursePojo) {
        Course course = new Course();
        course.setCourseName(coursePojo.getCourseName());
        course.setDurationHours(coursePojo.getDurationHours());
        return course;
    }
}
