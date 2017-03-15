package com.finrascreening.example.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class FileMeta {
	
	@Id
	@GeneratedValue
	private Long id;
	
	private String owner;
	private String name;
	private Long size;
	private Date postedOn;
	private String destinationDirectoy;
	
	//private const required as per JPA
	private FileMeta(){
		
	}
	
    public FileMeta(String name){
		this.name=name;
	}
    
    public FileMeta(String name, String owner, Long size, Date posetedOn, String destinationDirectory){
		this.name=name;
		this.owner = owner;
		this.size = size;
		this.postedOn = posetedOn;
		this.destinationDirectoy = destinationDirectory;
	}


	public Long getId() {
		return id;
	}


	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getSize() {
		return size;
	}

	public void setSize(Long size) {
		this.size = size;
	}
	public Date getPostedOn() {
		return postedOn;
	}

	public void setPostedOn(Date postedOn) {
		this.postedOn = postedOn;
	}

	public String getDestinationDirectoy() {
		return destinationDirectoy;
	}

	public void setDestinationDirectoy(String destinationDirectoy) {
		this.destinationDirectoy = destinationDirectoy;
	}

	@Override
	public String toString() {
		return "FileMeta [id=" + id + ", name=" + name + ", size=" + size + ", dateAdded=" + postedOn + "]";
	}

}
