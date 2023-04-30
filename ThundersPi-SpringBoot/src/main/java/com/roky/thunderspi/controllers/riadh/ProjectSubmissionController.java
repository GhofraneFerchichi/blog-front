package com.roky.thunderspi.controllers.riadh;


import com.roky.thunderspi.entities.ProjectSubmission;
import com.roky.thunderspi.services.riadh.IProjectSubmissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/projectSub")
@RequiredArgsConstructor
public class ProjectSubmissionController {

    private final IProjectSubmissionService IProjectSubmissionServiceImp;

    @GetMapping
    public Set<ProjectSubmission> getAllSubmissions()
    {
        return IProjectSubmissionServiceImp.getAllProjectSubmissions();
    }

    @PostMapping
    public void addProjectSubmisison(@RequestParam ProjectSubmission p)
    {
        IProjectSubmissionServiceImp.addProjectSubmission(p);
    }

    @PutMapping
    public void updateProjectSubmission(@RequestParam ProjectSubmission p)
    {
        IProjectSubmissionServiceImp.updateProjectrSubmission(p);
    }

    @DeleteMapping("/{id}")
    public void deleteProjectSubmission(@PathVariable Long id)
    {
        IProjectSubmissionServiceImp.deleteProjectSubmission(id);
    }

    @GetMapping("/{id}")
    public ProjectSubmission getProjectSubmissionById(@PathVariable Long id)
    {
        return IProjectSubmissionServiceImp.getProjectSubmissionById(id);
    }

    @GetMapping("/student/{id}")
    public Set<ProjectSubmission> getProjectSubmissionByStudent(Long id)
    {
        return IProjectSubmissionServiceImp.getProjectSubmissionsByStudentId(id);
    }
}
