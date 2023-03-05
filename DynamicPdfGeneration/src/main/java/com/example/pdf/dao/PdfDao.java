package com.example.pdf.dao;

import java.io.ByteArrayInputStream;

import org.springframework.stereotype.Controller;

import com.example.pdf.model.PdfModel;

@Controller 
public interface PdfDao {
	
	public ByteArrayInputStream createPdf(PdfModel model);
	

}
