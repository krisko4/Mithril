package com.website.demo.cloudinary;


import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class CloudinaryConfig {

    @Value("${cloudinary.api.key}")
    private String CLOUDINARY_API_KEY;
    @Value("${cloudinary.api.secret}")
    private String CLOUDINARY_API_SECRET;
    @Value("${cloudinary.name}")
    private String CLOUDINARY_NAME;
    @Value("${cloudinary.url}")
    private String CLOUDINARY_URL;
    private Cloudinary cloudinary;

    @PostConstruct
    public void init(){
        cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", CLOUDINARY_NAME,
                "api_key", CLOUDINARY_API_KEY,
                "api_secret", CLOUDINARY_API_SECRET,
                "secure", true
        ));
    }

    public Cloudinary getCloudinary(){
        return cloudinary;
    }

//    public Cloudinary getCloudinary(){
//        return new Cloudinary(ObjectUtils.asMap(
//                "cloud_name", CLOUDINARY_NAME,
//                "api_key", CLOUDINARY_API_KEY,
//                "api_secret", CLOUDINARY_API_SECRET,
//                "secure", true
//        ));
//    }

    public String getURL(){
        return CLOUDINARY_URL;
    }

}
