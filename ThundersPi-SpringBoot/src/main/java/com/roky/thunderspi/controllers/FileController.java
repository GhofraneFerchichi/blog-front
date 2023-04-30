package com.roky.thunderspi.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.roky.thunderspi.entities.FileDB;
import com.roky.thunderspi.message.ResponseFile;
import com.roky.thunderspi.message.ResponseMessage;
import com.roky.thunderspi.repositories.PostRepo;
import com.roky.thunderspi.services.FileStorageService;

import lombok.extern.slf4j.Slf4j;



@CrossOrigin(origins = "http://localhost:4200/",exposedHeaders="Access-Control-Allow-Origin" )
@Slf4j
@RestController
@RequestMapping("/File")
public class FileController {
  @Autowired
  private FileStorageService storageService;
  @Autowired
  PostRepo postrepo;
  @PostMapping("/upload")
  public ResponseEntity<ResponseMessage> uploadFile(@RequestPart("file") MultipartFile file) {
    String message = "";
    try {
      storageService.store(file);
      message = "Uploaded the file successfully: " + file.getOriginalFilename();
      return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
    } catch (Exception e) {
      message = "Could not upload the file: " + file.getOriginalFilename() + "!";
      return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
    }
  }
  @PostMapping("/uploadf")
  public Long uploadFilef(@RequestPart("file") MultipartFile file) throws IOException {
  
      return storageService.store1(file);
     
      
   
  }
  	@DeleteMapping("/delete-file/{id-file}")
	@ResponseBody
	public void deletefile( @PathVariable("id-file") Long idfile){
		storageService.deletefile(idfile);
  	}
  @GetMapping("/files")
  public ResponseEntity<List<ResponseFile>> getListFiles() {
    List<ResponseFile> files = storageService.getAllFiles().map(dbFile -> {
      String fileDownloadUri = ServletUriComponentsBuilder
          .fromCurrentContextPath()
          .path("/SpringMVC/File/files/")
          .path(dbFile.getId().toString())
          .toUriString();
      return new ResponseFile(
          dbFile.getName(),
          fileDownloadUri,
          dbFile.getType(),
          dbFile.getData().length);
    }).collect(Collectors.toList());
    return ResponseEntity.status(HttpStatus.OK).body(files);
    
  }

  @GetMapping("/file/{id}")
  public ResponseEntity<byte[]> getFile(@PathVariable Long id) {
    FileDB fileDB = storageService.getFile(id);
    return ResponseEntity.ok()
        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDB.getName() + "\"")
        .body(fileDB.getData());
  }

  @GetMapping("/filesdetail/{id}")
  public FileDB getFiledetail(@PathVariable Long id) {
    return storageService.getFile(id);
    
   
    
  }
  @GetMapping("/filesdearticle/{id}")
  public FileDB getListFilesdearticle(@PathVariable Long id) {
	  FileDB files = storageService.getFileBypost(id);
    return files;
}

  @PutMapping("/affecter-fileToArticle/{id-post}/{files}")
	@ResponseBody
	public void affecterFilesToarticle(@PathVariable("files") Long idfile, @PathVariable("id-Article") Long idArticle) {
	  storageService.affecterFileTopost(idfile, idArticle);

	}


  
}