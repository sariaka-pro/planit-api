package com.descodeuses.planit.service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.descodeuses.planit.entity.LogDocument;
import com.descodeuses.planit.repository.LogDocumentRepository;

@Service
public class LogDocumentService {

    @Autowired
    private LogDocumentRepository repo; 

    public void addLog(String text, LocalDateTime date) {
        LogDocument doc = new LogDocument(); 
        doc.setText(text);
        doc.setTimestamp(date);
        
        Map<String, Object> extras = new HashMap<>();
        extras.put("login","alice123");
        extras.put("language", "français");

        doc.setExtras(extras);
        repo.save(doc);
    }
}


