package com.example.pdf.controller;


import java.io.ByteArrayInputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.pdf.dao.PdfDao;
import com.example.pdf.model.PdfModel;


@Controller
@RequestMapping("/pdf")
public class PdfController {
	
	@Autowired 
	private PdfDao dao; 
	
	
	
	@PostMapping("/create")  
	public ResponseEntity<InputStreamResource> handler(@RequestBody  PdfModel pdf) {
		 ByteArrayInputStream createPdf = this.dao.createPdf(pdf);
		 HttpHeaders httpHeaders = new HttpHeaders();
		 httpHeaders.add("Content-Disposition", "inline;file=items.pdf");
		 return ResponseEntity.ok().headers(httpHeaders).contentType(MediaType.APPLICATION_PDF).body(new InputStreamResource(createPdf));
		
	}
	
	

}
