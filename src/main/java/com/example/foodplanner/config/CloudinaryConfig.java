package com.example.foodplanner.config;

import com.cloudinary.Cloudinary;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class CloudinaryConfig {

    @Value("dmd3mttpc")
    private String cloudName;
    @Value("799357567129288")
    private String apiKey;
    @Value("-0ZR-Nt4ESQJ_BVBqAzyryTUAO0")
    private String apiSecret;

    @Bean
    public Cloudinary createCloudinaryConfig() {
        Map<String, Object> config = new HashMap<>();
        config.put("cloud_name", "dmd3mttpc");
        config.put("api_key", "799357567129288");
        config.put("api_secret", "-0ZR-Nt4ESQJ_BVBqAzyryTUAO0");
        return new Cloudinary(config);
    }
}
