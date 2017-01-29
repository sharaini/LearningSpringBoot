package com.example.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

@RestController
public class PdfConvertor {
	@Value("${spring.http.multipart.location}")
	private String root;
	
	
	@RequestMapping(value="/fileconvert",method=RequestMethod.POST)
	public String fileupload(@RequestPart("file")MultipartFile file) throws IllegalStateException, IOException, DocumentException{
		
		Document pdfdocument=new Document();		
		File vf=new File(root+"/"+System.currentTimeMillis()+".doc");	
		System.out.println(vf.getAbsolutePath());
		file.transferTo(vf);		
		XWPFWordExtractor docfile=new XWPFWordExtractor(new XWPFDocument(new FileInputStream(vf)));
		PdfWriter.getInstance(pdfdocument,new FileOutputStream(root+"/"+System.currentTimeMillis()+".pdf"));		
		pdfdocument.open();
		pdfdocument.add(new Paragraph("Successfully generated pdf from your doc file"));
		pdfdocument.add(new Paragraph(docfile.getText()));		
		pdfdocument.close();
		return "success";
	}
}
