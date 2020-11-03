package com.jimds.buyers.util;

import com.jimds.buyers.constants.AppConstants;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.validation.constraints.NotBlank;


@ConfigurationProperties(prefix = AppConstants.FILE_PROPERTIES_PREFIX)
public class FileStorageProperties {
    @NotBlank
    private String location;

    public String getLocation(){
        return this.location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
