package com.ashsoft.view;

import java.awt.Color;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.ashsoft.model.PurchaseDtl;
import com.ashsoft.model.PurchaseOrder;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

public class VendorInvoicePdfView extends AbstractPdfView {

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//Set file name
		response.addHeader("Content-Disposition", "attachment;filename=VEN-INV.pdf");
		
		//read data
		PurchaseOrder po = (PurchaseOrder) model.get("po");
		List<PurchaseDtl> poDtls = (List<PurchaseDtl>) model.get("poDtls");
		
		double finalCost = 0.0;
		
		for(PurchaseDtl dtl:poDtls)
		{
			finalCost += (dtl.getQty()*Float.parseFloat(dtl.getPart().getBaseCost()));
		}
		
		//font = font family + size + style + color
		
		Font titleFont = new Font(Font.HELVETICA, 25 , Font.BOLD, Color.RED);
		
		//Paragraph = text + font
		
		Paragraph p =  new Paragraph("VENDOR INVOICE PDF",titleFont);
		
		p.setAlignment(Element.ALIGN_CENTER);
		
		document.add(p); 
		
		Font hFont = new Font(Font.HELVETICA, 14, Font.BOLD,Color.BLUE);
		
		PdfPTable hTable = new PdfPTable(4);
		
		p.setAlignment(Element.ALIGN_CENTER);
		
		//add space before the current table
		
		hTable.setSpacingBefore(20.0f);
		
		//add space after the current table
		hTable.setSpacingAfter(15.0f);
		
		hTable.addCell("VENDOR CODE");
		hTable.addCell( new Phrase(po.getVendor().getUserCode(),hFont));
		
		//Phrase = small text + font
		hTable.addCell("ORDER CODE");
		hTable.addCell(new Phrase(po.getOrderCode(),hFont));
		
		hTable.addCell("FINAL COST");
		hTable.addCell(new Phrase(String.valueOf(finalCost),hFont));
		
		hTable.addCell("SHIPMENT CODE");
		hTable.addCell(new Phrase(po.getShipmentType().getShipmentCode(),hFont));
		
		document.add(hTable);
		
		Font dFont = new Font(Font.HELVETICA, 12 , Font.BOLD, Color.MAGENTA);
		
		PdfPTable dtable = new PdfPTable(4);
		
		//pixel sizes for a table column
		dtable.setWidths(new float[] {3.5f, 1.0f, 1.0f, 1.5f});
		dtable.setSpacingAfter(20.0f);
		
		dtable.addCell(new Phrase("CODE",dFont));
		dtable.addCell(new Phrase("BASE COST",dFont));
		dtable.addCell(new Phrase("QTY",dFont));
		dtable.addCell(new Phrase("TOTAL COST",dFont));
		
		for(PurchaseDtl dtl:poDtls)
		{
			dtable.addCell(dtl.getPart().getPartCode());
			dtable.addCell(dtl.getPart().getBaseCost());
			dtable.addCell(dtl.getQty().toString());
			dtable.addCell(String.valueOf((Float.parseFloat(dtl.getPart().getBaseCost())) *
						    dtl.getQty())
					);
		}
		
		document.add(dtable);
		
		document.add(new Paragraph(new Date().toString()));
		
		
	}

}
