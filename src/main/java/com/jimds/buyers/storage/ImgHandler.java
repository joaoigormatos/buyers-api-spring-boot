package com.jimds.buyers.storage;


import com.jimds.buyers.exceptions.ImageTypeNotAllowed;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

@Component
public class ImgHandler implements IStorage{



    public String uploadImage(MultipartFile imageFile) throws Exception{
        String folder = "/upload/photos/";
        String imageName = imageFile.getOriginalFilename();
        String imageType = imageName.split(".")[imageName.length() -1];

        byte[] image = imageFile.getBytes();

        if(verifyType(imageType)){
            String generatedNumber = generateRandomName();
            String imageGeneratedName = folder+generatedNumber+"."+imageType;

            Path path = Paths.get(imageGeneratedName);
            Files.write(path,image);
            return imageGeneratedName;
        }

        throw new ImageTypeNotAllowed();

    }

    private boolean verifyType(String imageType){
        if(imageType.equals("png") || imageType.equals("jpeg") || imageType.equals("PNG") || imageType.equals("JPEG")){
            return true;
        }
        return false;
    }

    private String generateRandomName(){
        Random randomNumber = new Random();
        int number = randomNumber.nextInt(100000)+1;

        Long result =  Long.valueOf(number) * System.currentTimeMillis();

        return String.valueOf(result);
    }


    @Override
    public String storeFile(MultipartFile file) throws IOException {
        return null;
    }

    @Override
    public Resource loadFileAsResource(String fileName) {
        return null;
    }
}
