package com.example.pdf.repo;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.UUID;

import org.springframework.stereotype.Service;
import com.example.pdf.dao.PdfDao;
import com.example.pdf.model.Items;
import com.example.pdf.model.PdfModel;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;


@Service 
public class PdfRepository implements PdfDao {

	@Override
	public ByteArrayInputStream createPdf(PdfModel model) {
	 
		 	
		 ByteArrayOutputStream  out = new ByteArrayOutputStream();
		 Document document = new Document();
		 
		 
		 
		    //  save pdf file 
		
		    UUID uuid = UUID.randomUUID();
		    File file = new File("D://Folder//"+uuid+".pdf");
		    FileOutputStream pdfFileout;
		    
			try {
				pdfFileout = new FileOutputStream(file);
				PdfWriter.getInstance(document, pdfFileout);
			} catch (FileNotFoundException e2) {
		           e2.printStackTrace();
			} catch (DocumentException e) {
				e.printStackTrace();
			}
		   
			
		 
		 try {
			PdfWriter.getInstance(document, out);
			document.open();
			Font fontFacory = FontFactory.getFont(FontFactory.COURIER);
			Paragraph seller = new Paragraph("Seller: \n"+model.getSeller()+"\n"+model.getSellerAddress()+"\n"+model.getSellerGstin(),fontFacory);
			Paragraph buyer = new Paragraph("Buyer: \n"+model.getBuyer()+"\n"+model.getBuyerAddress()+"\n"+model.getBuyerGstin(),fontFacory);

			
			
			    PdfPTable table = new PdfPTable(4); // 3 columns.
		        table.setWidthPercentage(100);
		        table.setSpacingBefore(30f); // Space before table
		        table.setSpacingAfter(10f);// Width 100%
		        
		        

		        PdfPCell sellerCell = new PdfPCell(seller);
		        sellerCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		        sellerCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		        sellerCell.setPaddingTop(16);            
		        sellerCell.setPaddingLeft(22);
		        sellerCell.setPaddingBottom(16);
		        
		        sellerCell.setColspan(2);
		       
		        
		        PdfPCell buyerCell = new PdfPCell(buyer);
		        buyerCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		        buyerCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		        buyerCell.setPaddingTop(16);            
		        buyerCell.setPaddingLeft(22);
		        buyerCell.setPaddingBottom(16);
		        
		        buyerCell.setColspan(2);
		        
		        
		        table.addCell(sellerCell);
		        table.addCell(buyerCell);
		        
		        PdfPCell item = new PdfPCell(new Paragraph("Item",fontFacory));
		        item.setHorizontalAlignment(Element.ALIGN_CENTER);
		        item.setFixedHeight(25f);
		        item.setVerticalAlignment(Element.ALIGN_MIDDLE);
		        
		        PdfPCell quantity = new PdfPCell(new Paragraph("Quantity",fontFacory));
		        quantity.setFixedHeight(25f);
		        quantity.setHorizontalAlignment(Element.ALIGN_CENTER);
		        quantity.setVerticalAlignment(Element.ALIGN_MIDDLE);
		        
		        PdfPCell rate = new PdfPCell(new Paragraph("Rate",fontFacory));
		        rate.setFixedHeight(25f);
		        rate.setHorizontalAlignment(Element.ALIGN_CENTER);
		        rate.setVerticalAlignment(Element.ALIGN_MIDDLE);
		        
		        PdfPCell amount = new PdfPCell(new Paragraph("Amount",fontFacory));
		        amount.setFixedHeight(25f);
		        amount.setHorizontalAlignment(Element.ALIGN_CENTER);
		        amount.setVerticalAlignment(Element.ALIGN_MIDDLE);
		        
		        
		        table.addCell(item);
		        table.addCell(quantity);
		        table.addCell(rate);
		        table.addCell(amount);
		        
		        
		        
		        
		        //  add all product to table 
		        
		        if(model.getItems().size()>0) {
		        	
		        	 for(Items i :model.getItems()) {
				        	
				        	

					        PdfPCell itemValue = new PdfPCell(new Paragraph(i.getName(),fontFacory));
					        itemValue.setHorizontalAlignment(Element.ALIGN_CENTER);
					        itemValue.setFixedHeight(25f);
					        itemValue.setVerticalAlignment(Element.ALIGN_MIDDLE);
					        
					        PdfPCell quantityValue = new PdfPCell(new Paragraph(i.getQuantity(),fontFacory));
					        quantityValue.setHorizontalAlignment(Element.ALIGN_CENTER);
					        quantityValue.setVerticalAlignment(Element.ALIGN_MIDDLE);
					        
					        PdfPCell rateValue = new PdfPCell(new Paragraph(Double.toString(i.getRate()),fontFacory));
					        rateValue.setHorizontalAlignment(Element.ALIGN_CENTER);
					        rateValue.setVerticalAlignment(Element.ALIGN_MIDDLE);
					        
					        PdfPCell amountValue = new PdfPCell(new Paragraph(Double.toString(i.getAmount()),fontFacory));
					        amountValue.setHorizontalAlignment(Element.ALIGN_CENTER);
					        amountValue.setVerticalAlignment(Element.ALIGN_MIDDLE);
					        
					        
					        table.addCell(itemValue);
					        table.addCell(quantityValue);
					        table.addCell(rateValue);
					        table.addCell(amountValue);
				        	
				        	
				        	
				       }
		        	 
		        }
		       
		        
				document.add(table);
			
			document.close();
			
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		 
		
		 
		return new ByteArrayInputStream(out.toByteArray());
		
	}
	
	

}
