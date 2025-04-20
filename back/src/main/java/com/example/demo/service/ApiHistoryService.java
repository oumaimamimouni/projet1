package com.example.demo.service;

import com.example.demo.model.ApiHistory;
import com.example.demo.repository.ApiHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApiHistoryService {

    @Autowired
    private ApiHistoryRepository repository;

    // Méthode pour récupérer tous les historiques des API
    public List<ApiHistory> getAllApiHistory() {
        return repository.findAll();
    }

    // Méthode pour récupérer un historique d'API par ID
    public ApiHistory getApiHistoryById(Long id) {
        return repository.findById(id).orElse(null); // Retourne null si l'ID n'existe pas
    }
}
