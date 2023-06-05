package com.example.demo.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.ImageData;
import com.example.demo.repo.StorageRepo;
import com.example.demo.util.ImageUtil;

@Service
public class StorageService {
	
	@Autowired
	StorageRepo repo;
	
	
	public String uploadImage(MultipartFile multipartFile) throws IOException {
		
		ImageData imageData= repo.save(ImageData.builder()
				.name(multipartFile.getOriginalFilename())
		.type(multipartFile.getContentType())
		.imageData(ImageUtil.compressImage(multipartFile.getBytes())).build());
		
		if(imageData!=null) {
			return "File Uploaded sucessfully"+multipartFile.getOriginalFilename();
		}
		return null;
	}
	
	public byte[] downloadImage(String fileName) {
		
		Optional<ImageData> dbImageData=repo.findByName(fileName);
		byte[] image=ImageUtil.decompressImage(dbImageData.get().getImageData());
		return image;
	}
	public List<ImageData> allIma() {
		return repo.findAll();
	}

	

}
