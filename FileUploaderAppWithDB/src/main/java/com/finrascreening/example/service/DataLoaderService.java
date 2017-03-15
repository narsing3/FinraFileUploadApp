package com.finrascreening.example.service;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.finrascreening.example.domain.FileMeta;
import com.finrascreening.example.repository.FileRepository;

public class DataLoaderService {


	   @Autowired
	   FileRepository fileRepository;
	   
	   
	   @PostConstruct
	   public void persistFileMeta(FileMeta filemeta){
		   fileRepository.save(filemeta);
	   }
	   
	   
}
