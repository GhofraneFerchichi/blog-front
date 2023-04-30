package com.roky.thunderspi.services.riadh;


import com.roky.thunderspi.entities.ProjectFile;
import com.roky.thunderspi.repositories.riadh.ProjectFileRepository;
import com.roky.thunderspi.repositories.riadh.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Set;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class IProjectFileServiceImp implements IProjectFileService {


    private final ProjectFileRepository projectFileRepository;
    private final ProjectRepository projectRepository;


    //store Project File inside project
    //seperate between project and projectSubmission
    public ProjectFile store(MultipartFile file, Long projectId ) throws IOException
    {
        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        ProjectFile projectFile = new ProjectFile(filename,file.getContentType(), file.getBytes());

        return projectFileRepository.save(projectFile);
    }

    public ProjectFile getFile(Long id)
    {
        return projectFileRepository.findById(id).get();
    }

    public Stream<ProjectFile> getAllFiles()
    {
        return projectFileRepository.findAll().stream();
    }

    @Override
    public Set<ProjectFile> getAllFilesByProject(Long id) {
        return null;
    }

    @Override
    public Set<ProjectFile> getAllFilesByProjectSubmission(Long id) {
        return null;
    }
}
