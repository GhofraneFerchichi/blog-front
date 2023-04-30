package com.roky.thunderspi.controllers;


import com.roky.thunderspi.entities.Course;
import com.roky.thunderspi.entities.CourseSubject;
import com.roky.thunderspi.entities.Project;
import com.roky.thunderspi.entities.User;
import com.roky.thunderspi.repositories.CourseRepo;
import com.roky.thunderspi.repositories.CourseSubjectRepo;
import com.roky.thunderspi.repositories.UserRepo;
import com.roky.thunderspi.services.ICourseService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/course")
@AllArgsConstructor
public class CourseController {
    private ICourseService iCourseService;
    @Autowired
    CourseRepo courseRepo;
    @Autowired
    CourseSubjectRepo courseSubjectRepo;
    @Autowired
    UserRepo userRepo;


    @GetMapping("/getAll")
    public List<Course> findAllCourses() {
        return iCourseService.findAllCourses();
    }

    @GetMapping("getAll/{id}")
    public Course findCoursesById(@PathVariable Long id) {
        return iCourseService.findCouresById(id);
    }

    @PostMapping("/addCourse/{iduser}/{idsc}")
    public Course addCourse(@RequestBody Course course, @PathVariable Long iduser, @PathVariable Long idsc) {
        User user = userRepo.findById(iduser).orElse(null);
        CourseSubject courseSubject = courseSubjectRepo.findById(idsc).orElse(null);
        course.setCourseSubject(courseSubject);
        course.setUser(user);
        return courseRepo.save(course);
    }

    @PutMapping("/update/{id}")
    public Course editCourse(@RequestBody Course course, @PathVariable Long id) {
        Course ExistantCourse = courseRepo.findById(id).orElseThrow(null);
        ExistantCourse.setCourseLanguage((course.getCourseLanguage()));
        ExistantCourse.setEducationLevel(course.getEducationLevel());
        ExistantCourse.setName(course.getName());
        ExistantCourse.setLength(course.getLength());

        return courseRepo.saveAndFlush(ExistantCourse);
    }

    @DeleteMapping("/delete/{idCourse}")
    public void deleteCourse(@PathVariable Long idCourse) {
        iCourseService.deleteCourses(idCourse);
    }


    @GetMapping("/teacher/{id}")
    public List<Course> getCourseByTeacherId(@PathVariable Long id) {
        return courseRepo.getCourseByTeachersId(id);
    }

    @GetMapping("/subject")
    public List<Object> getCourseBySubject() {
        return courseRepo.getCourseBySubject();
    }


}
