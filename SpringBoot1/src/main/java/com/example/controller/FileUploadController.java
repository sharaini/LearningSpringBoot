package com.example.controller;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class FileUploadController {

	@Value("${spring.http.multipart.location}")
	private String root;
	
	
	@RequestMapping(value="/fileupload",method=RequestMethod.POST)
	public String fileupload(@RequestPart("file")MultipartFile file) throws IllegalStateException, IOException{
		
		File vf=new File(root+"/"+System.currentTimeMillis()+".sharath");
		
		file.transferTo(vf);
		return "Success";
	}
}
