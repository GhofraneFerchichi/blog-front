package com.roky.thunderspi.controllers.riadh;



import com.roky.thunderspi.entities.ProjectFile;
import com.roky.thunderspi.message.ResponseFile;
import com.roky.thunderspi.message.ResponseMessage;
import com.roky.thunderspi.services.riadh.IProjectFileServiceImp;
import com.roky.thunderspi.services.riadh.IProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/projectFile")
@RequiredArgsConstructor
public class ProjectFIleController {


    private final IProjectFileServiceImp projectFileService;

    private final IProjectService IProjectServiceImp;



    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage> uploadProjectFile(@RequestParam("file")MultipartFile file, Long projectid)
    {
        String message = "";
        try {
            projectFileService.store(file, projectid);

            message = "Uploaded File successfully: " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        }catch (Exception e)
        {
            message = "Could not upload file: " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }

    @GetMapping("/files")
    public ResponseEntity<List<ResponseFile>> getListProjectFiles(){
        List<ResponseFile> files = projectFileService.getAllFiles().map(projectFile ->
        {
            String fileDownloadUri = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/files")
                    .path(projectFile.getId().toString())
                    .toUriString();
            return new ResponseFile(projectFile.getName(),fileDownloadUri,projectFile.getType(),projectFile.getData().length);

        }).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(files);
    }

    @GetMapping("/files/{id}")
    public ResponseEntity<byte[]> getFileById(@PathVariable Long id)
    {
        ProjectFile projectFile = projectFileService.getFile(id);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachement; filename=\"" + projectFile.getName() + "\"")
                .body(projectFile.getData());
    }

    @GetMapping("/project/{id}")
    public ResponseEntity<List<ResponseFile>> getFilesByProjectId(@PathVariable Long id)
    {
        List<ResponseFile> files = IProjectServiceImp.getProjectById(id).getProjectFiles().stream().map(projectFile ->
        {
            String fileDownloadUri = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/files")
                    .path(projectFile.getId().toString())
                    .toUriString();
            return new ResponseFile(projectFile.getName(),fileDownloadUri,projectFile.getType(),projectFile.getData().length);

        }).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(files);
    }
}
