package com.example.demo.repository;

import com.example.demo.model.ApiHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApiHistoryRepository extends JpaRepository<ApiHistory, Long> {
    // Vous pouvez ajouter des méthodes personnalisées si nécessaire
}
