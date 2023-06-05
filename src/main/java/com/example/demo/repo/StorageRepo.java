package com.example.demo.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.ImageData;

public interface StorageRepo extends JpaRepository<ImageData, Long>{
	
	Optional<ImageData> findByName(String name);

}
