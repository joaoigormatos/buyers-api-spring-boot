package com.jimds.buyers.service;

import com.jimds.buyers.exceptions.FileStorageException;
import com.jimds.buyers.util.FileStorageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Random;

@Service
public class FileStorageService {

    private final Path fileStorageLocation;
    private String location;

    @Autowired
    public FileStorageService(FileStorageProperties  fileStorageProperties) {
        this.fileStorageLocation = Paths.get(fileStorageProperties.getLocation()).toAbsolutePath()
                .normalize();
        this.location = fileStorageProperties.getLocation();
        try{
            Files.createDirectories(this.fileStorageLocation);
        }
        catch (Exception e){
            throw new FileStorageException("Could not create the directory where the uploaded files will be stored.", e);        }
    }

    public String getUrl(String address, String imageName){
        StringBuilder stringBuilder = new StringBuilder();
        String subLocation =  location.substring(1);
        return stringBuilder.append(address).append(subLocation).append("/").append(imageName).toString();
    }

    //TODO: Verify the type of the image;
    public String storeFile(MultipartFile file){
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        String fileType = file.getContentType().split("/")[1];
        try{
            if(fileName.contains("..")){
                throw new FileStorageException("Sorry! Filename contains invalid path sequence" + fileName);
            }
            String randomName = generateRandomName();
            randomName+= "."+ fileType;
            Path targetLocation  = this.fileStorageLocation.resolve(randomName);

            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            return randomName;
        }
        catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    private String generateRandomName(){
        Random random =  new Random();
        Long result = Long.sum(System.currentTimeMillis(),random.nextLong());
        return Long.toString(result);
    }

    public void delete(String imageURL) throws IOException {
        Path imagePath = Paths.get(this.fileStorageLocation+"/"+imageURL).toAbsolutePath().normalize();
        Files.delete( imagePath);
    }
}
