package com.example.foodplanner.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {
	
	public void uploadFile(MultipartFile file, String prefix);

}
