package com.example.demo.controller;

import com.example.demo.model.ApiHistory;
import com.example.demo.service.ApiHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ApiHistoryController {

    @Autowired
    private ApiHistoryService apiHistoryService;

    // Endpoint pour récupérer tous les historiques des API
    @GetMapping("/history")
    public ResponseEntity<List<ApiHistory>> getApiHistory() {
        List<ApiHistory> apiHistoryList = apiHistoryService.getAllApiHistory();
        return ResponseEntity.ok(apiHistoryList);
    }

    // Nouvelle méthode pour récupérer un historique d'API par ID
    @GetMapping("/history/{id}")
    public ResponseEntity<ApiHistory> getApiHistoryById(@PathVariable Long id) {
        ApiHistory apiHistory = apiHistoryService.getApiHistoryById(id);
        if (apiHistory == null) {
            return ResponseEntity.notFound().build(); // Retourne un 404 si l'ID n'existe pas
        }
        return ResponseEntity.ok(apiHistory); // Retourne l'historique avec un code de statut 200 OK
    }
}

