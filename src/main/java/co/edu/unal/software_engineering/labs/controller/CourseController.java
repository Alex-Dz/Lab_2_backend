package co.edu.unal.software_engineering.labs.controller;

import co.edu.unal.software_engineering.labs.model.Association;
import co.edu.unal.software_engineering.labs.model.Course;
import co.edu.unal.software_engineering.labs.model.Period;
import co.edu.unal.software_engineering.labs.model.User;
import co.edu.unal.software_engineering.labs.pojo.CoursePOJO;
import co.edu.unal.software_engineering.labs.pojo.EnrolledCoursePOJO;
import co.edu.unal.software_engineering.labs.service.AssociationService;
import co.edu.unal.software_engineering.labs.service.CourseService;
import co.edu.unal.software_engineering.labs.service.PeriodService;
import co.edu.unal.software_engineering.labs.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
public class CourseController {

    private final CourseService courseService;

    private final AssociationService associationService;
    private final UserService userService;

    private final PeriodService periodService;

    public CourseController(CourseService courseService, AssociationService associationService, UserService userService, PeriodService periodService) {
        this.courseService = courseService;
        this.associationService = associationService;
        this.userService = userService;
        this.periodService = periodService;
    }

    @PostMapping(value = {"/profesor/crear-curso"})
    public ResponseEntity<Void> createCourse(@RequestBody CoursePOJO coursePojo) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findByUsername(username);
        /*if (user.getRoles().get(0).getRoleName().equals("Profesor")) {
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }*/
        Course course = courseService.mapperCourseEntity(coursePojo);
        if (!courseService.isRightCourse(course)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        course = courseService.save(course);
        Period period = periodService.findLast();
        associationService.associate(user, user.getRoles().get(0), course, period);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/mis-cursos")
    public List<EnrolledCoursePOJO> getCoursesByUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findByUsername(username);
        List<Association> associations = associationService.getAssociationsByUser(user);
        List<EnrolledCoursePOJO> enrolledCourses = new ArrayList<>();
        for (Association association : associations) {
            enrolledCourses.add(new EnrolledCoursePOJO(association));
        }
        return enrolledCourses;
    }

}