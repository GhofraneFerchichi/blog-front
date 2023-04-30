package com.roky.thunderspi.services.riadh;


import com.roky.thunderspi.entities.Project;
import com.roky.thunderspi.entities.User;
import com.roky.thunderspi.repositories.UserRepo;
import com.roky.thunderspi.repositories.riadh.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class IProjectServiceImp implements IProjectService{

    private final ProjectRepository projectRepository;
    private final UserRepo userRepo;

    public Project addProject(Project p)
    {
        //Checking the existence of the user in the JSON request
        User u = userRepo.findById(p.getUser().getId()).orElse(null);
        Assert.notNull(u,"No User Found");
        //Setting the user for the project
        p.setUser(u);
        return projectRepository.save(p);
    }
    public Project updateProject(Project p)
    {
        Assert.notNull(projectRepository.findById(p.getId()));
        return projectRepository.save(p);
    }

    public void deleteProject(Long id)
    {
        projectRepository.deleteById(id);
    }

    public Project getProjectById(Long id)
    {
        return projectRepository.findById(id).orElse(null);
    }

    public Stream<Project> getAllProjects()
    {
        return projectRepository.findAll().stream();
    }


    public Stream<Project> getProjectsByTeacher(Long id){
        return null;
    }

    @Override
    public Stream<Project> getProjectsByNumberOfNumberPassed() {
        return null;
    }

    @Override
    public Stream<Project> getProjectsByNumberOfEnrollments() {
        return null;
    }

    @Override
    public Stream<Project> getProjectsByCourse(Long courseId) {
        return null;
    }
}
