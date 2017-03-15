package com.finrascreening.example.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.finrascreening.example.domain.FileMeta;

public interface FileRepository extends CrudRepository<FileMeta, Long>{

	List<FileMeta> findAllByOwnerOrderByPostedOnDesc(String owner);
	
	List<FileMeta> findAllByNameOrderByPostedOnDesc(String name);

	List<FileMeta> findAllBySizeLessThan(Long size);
}
