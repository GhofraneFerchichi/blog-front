package com.roky.thunderspi.controllers;

import com.roky.thunderspi.entities.Course;
import com.roky.thunderspi.entities.CourseSubject;
import com.roky.thunderspi.services.ICourseSubjectService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subject")
@AllArgsConstructor
public class CourseSubjectController {

    private ICourseSubjectService iCourseSubjectService;
    @GetMapping("/getAll")
    public List<CourseSubject> findAllCoursesSubject() {
        return iCourseSubjectService.findAllCoursesSubject();
    }

    @GetMapping("getAll/{id}")
    public CourseSubject findCoursesSubjectById(@PathVariable Long id) { return iCourseSubjectService.findCouresSubjectById(id);
    }

    @PostMapping("/add")
    public CourseSubject addCoursesSubject(@RequestBody CourseSubject courseSubject) { return iCourseSubjectService.addCoursesSubject(courseSubject);
    }

    @PutMapping("/update")
    public CourseSubject editCoursesSubject(@RequestBody CourseSubject courseSubject) {
        return iCourseSubjectService.addCoursesSubject(courseSubject);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteCoursesSubject(@PathVariable Long id) {
        iCourseSubjectService.deleteCoursesSubject(id);
    }
}

