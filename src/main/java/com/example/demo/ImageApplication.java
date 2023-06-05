package com.example.demo;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.ImageData;
import com.example.demo.service.StorageService;

@SpringBootApplication
@RestController
@RequestMapping("/image")
public class ImageApplication {
	
	@Autowired
	StorageService service;
	
	@PostMapping
	public ResponseEntity<?> uploadImage(@RequestParam("image")MultipartFile file) throws IOException{
		String uploadIMage=service.uploadImage(file);
		return ResponseEntity.status(HttpStatus.OK)
				.body(uploadIMage);
	}
	@GetMapping("/{fileName}")
	public ResponseEntity<?> downloadImage(@PathVariable String fileName){
		byte[] image=service.downloadImage(fileName);
		return ResponseEntity.status(HttpStatus.OK)
				.contentType(MediaType.valueOf("image/png"))
				.body(image);
	}
	@GetMapping("/all")
	public List<ImageData> allImage(){
		return service.allIma();
		
	}

	public static void main(String[] args) {
		SpringApplication.run(ImageApplication.class, args);
	}

}
