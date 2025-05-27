package com.example.FreshFarm.Delivery.service.impl;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.example.FreshFarm.Delivery.exception.CustomException;
import com.example.FreshFarm.Delivery.model.domain.Image;
import com.example.FreshFarm.Delivery.model.domain.Product;
import com.example.FreshFarm.Delivery.repository.ImageRepository;
import com.example.FreshFarm.Delivery.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {
    private final ImageRepository imageRepository;
    private final AmazonS3 s3;

    @Value("${application.bucket.name}")
    private String bucketName;

    @Override
    public Image save(MultipartFile file) {
        return imageRepository.save(saveImage(file));
    }

    @Override
    public Image save(MultipartFile file, Product product) {
        Image image = saveImage(file);
        image.setProduct(product);
        return imageRepository.save(image);
    }

    @Override
    public void delete(String filename) {
        s3.deleteObject(bucketName, filename);
    }

    private File convertMultipartFileToFile(MultipartFile file) {
        File convertFile = new File(Objects.requireNonNull(file.getOriginalFilename()));
        try (FileOutputStream fos = new FileOutputStream(convertFile)) {
            fos.write(file.getBytes());
        } catch (IOException e) {
            throw new CustomException("Error while converting multipart file", HttpStatus.BAD_GATEWAY);
        }
        return convertFile;
    }

    private Image saveImage(MultipartFile file) {
        if (file.isEmpty()) {
            throw new CustomException("Incorrect file", HttpStatus.BAD_REQUEST);
        }
        File convertedFile = convertMultipartFileToFile(file);
        String fileName = System.currentTimeMillis() + "_" + Objects.requireNonNull(file.getOriginalFilename()).replaceAll("\\s+", "_");;
        s3.putObject(new PutObjectRequest(bucketName, fileName, convertedFile)
                .withCannedAcl(CannedAccessControlList.PublicRead));
        boolean delete = convertedFile.delete();
        if (!delete) {
            throw new CustomException("Failed to delete file", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        Image image = new Image();
        image.setFilename(fileName);
        image.setPath(s3.getUrl(bucketName, fileName).toString());

        return imageRepository.save(image);
    }
}
