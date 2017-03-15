package com.finrascreening.example.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.finrascreening.example.domain.FileMeta;
import com.finrascreening.example.repository.FileRepository;



@Controller
public class FileUploadController {
	
	   @Autowired
	   FileRepository fileRepository;

	   private static final Logger logger = Logger.getLogger(FileUploadController.class);
	
	private FileMeta uploadedFileMeta = null; 
	
    @GetMapping("/")
    public String index() {
        return "upload";
    }

    
    @RequestMapping("/fetchFileMeta") 
    public String fetchFileMeta(@RequestParam(value = "owner")String owner,
                                   Model model) {
    	if(owner == null || owner.length()==0){
    		model.addAttribute("fileList", fileRepository.findAll());
    		
    	}else{
    		model.addAttribute("fileList", fileRepository.findAllByOwnerOrderByPostedOnDesc(owner));
	
    	}
    	
    	return "upload";
        
    }
    
    
    @RequestMapping("/fetchLessThanMaxSize") 
    public String fetchLessThanMaxSize(@RequestParam(value = "size")String size,
                                   Model model) {
    	if(size != null && size.trim().length()>0){
    		model.addAttribute("fileList", fileRepository.findAllBySizeLessThan(Long.parseLong(size)));
    		
    	}
    	
    	return "upload";
        
    }

    
    @RequestMapping("/upload") 
    public String singleFileUpload(@RequestParam(value = "file") MultipartFile file, @RequestParam(value = "owner") String owner, @RequestParam(value = "destination") String destination,
                                   RedirectAttributes redirectAttributes,Model model) {
    	
        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return "redirect:uploadStatus";
        }

        try {
            // Get the file and save it somewhere
        	
            byte[] bytes = file.getBytes();
            Path path = Paths.get(destination + file.getOriginalFilename());
            if (!Files.exists(path.getParent()))
                Files.createDirectories(path.getParent());
            Files.write(path, bytes);
        	

        	//Retrive Uploaded File metadata
            uploadedFileMeta = new FileMeta(file.getOriginalFilename());
            uploadedFileMeta.setOwner(owner);
        	uploadedFileMeta.setPostedOn(new Date());
        	uploadedFileMeta.setSize(file.getSize());
        	uploadedFileMeta.setDestinationDirectoy(destination);
        	
        	fileRepository.save(uploadedFileMeta);

        	logger.debug("Received fileMeta"+uploadedFileMeta.toString());

        	logger.debug("FIled load Success. Return to the upload page");

            model.addAttribute("message",
                    "You successfully uploaded '" + file.getOriginalFilename() + "'");
            model.addAttribute("fileList", fileRepository.findAll());

        } catch (IOException e) {

        	logger.error("File upload failed.");
    		model.addAttribute("message","File upload failed. Please retry");
            e.printStackTrace();
        }

        return "upload";
    }

    @GetMapping("/uploadStatus")
    public String uploadStatus() {
        return "uploadStatus";
    }
    

}