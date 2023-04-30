package com.roky.thunderspi.services;

import com.roky.thunderspi.entities.Image;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Set;
import java.util.stream.Stream;

public interface IImageService {
    public Image store(MultipartFile file ) throws IOException;

    public Image getFile(Long id);

    public Stream<Image> getAllFiles();
    public Set<Image> getAllFilesByLib(Long id);
    public Set<Image> getAllFilesByLibSubmission(Long id);
}
