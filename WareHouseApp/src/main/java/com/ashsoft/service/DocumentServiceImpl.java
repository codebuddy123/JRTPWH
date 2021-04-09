package com.ashsoft.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ashsoft.model.Document;
import com.ashsoft.repo.DocumentRepo;

@Service
public class DocumentServiceImpl implements IDocumentService {

	@Autowired
	private DocumentRepo repo;
	
	@Override
	public void saveDocument(Document doc) {
		repo.save(doc);
	}
	
	@Override
	public List<Object[]> getDocIdAndNames() {
		
		return repo.getDocIdAndNames();
	}
	
	@Override
	public Optional<Document> getDocumentById(Integer id) {
		
		return repo.findById(id);
	}

}
