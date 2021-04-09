package com.ashsoft.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.ashsoft.model.Document;
import com.ashsoft.service.IDocumentService;

@Controller
@RequestMapping("/documents")
public class DocumentController {
	
	@Autowired
	private IDocumentService service;
	
	//1. Show Documents Form
	@GetMapping("/show")
	public String showDocsForm(Model model)
	{
		List<Object[]> list = service.getDocIdAndNames();
		model.addAttribute("list", list);
		return "Documents";
	}
	
	//2. On click Upload Document
	
	@PostMapping("/upload")
	public String uploadDocument(@RequestParam("docId")
								Integer docId, @RequestParam("docOb") MultipartFile file) {
		
		if(file!=null)
		{
			Document doc = new Document();
			doc.setDocId(docId);
			doc.setDocName(file.getOriginalFilename());
			try {
				doc.setDocData(file.getBytes());
			} catch (IOException e) {
				e.printStackTrace();
			}
			service.saveDocument(doc);
		}
		
		return "redirect:show";
	}
	
	// Download the Document
	
	@GetMapping("/download")
	public void downloadDocument(@RequestParam("id") Integer id,HttpServletResponse response)
	{
		Optional<Document> opt = service.getDocumentById(id);
		if(opt.isPresent())
		{
			// read object
			Document doc = opt.get();
			
			//add Head Section
			response.addHeader("Content-Disposition", "attachment;filename="+doc.getDocName());
			
			//Copy data to Output Stream from docData
			try {
				FileCopyUtils.copy(doc.getDocData(), response.getOutputStream());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
}
