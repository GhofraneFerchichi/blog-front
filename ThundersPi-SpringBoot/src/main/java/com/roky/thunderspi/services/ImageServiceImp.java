package com.roky.thunderspi.services;

import com.roky.thunderspi.entities.Image;
import com.roky.thunderspi.repositories.imlageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Set;
import java.util.stream.Stream;
@Service
public class ImageServiceImp implements IImageService {
    @Autowired
    private imlageRepo imlageRepo;



    @Override
    public Image store(MultipartFile file) throws IOException {
        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        Image libFile = new Image(filename,file.getContentType(), file.getBytes());

        return imlageRepo.save(libFile);
    }

    @Override
    public  Image getFile(Long id) {
        return imlageRepo.findById(id).get();
    }


    public Stream<Image> getAllFiles() {
        return imlageRepo.findAll().stream();
    }

    @Override
    public Set<Image> getAllFilesByLib(Long id) {
        return null;
    }

    @Override
    public Set<Image> getAllFilesByLibSubmission(Long id) {
        return null;
    }
}