package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/api/auth") // Assure-toi que ça correspond au front-end
@CrossOrigin(origins = "http://localhost:4200") // Autorise les requêtes depuis Angular
public class AuthController {

    private final AuthService authService;

    // Injection via constructeur
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        String password = request.get("password");

        // Appel du service pour récupérer le token
        String token = authService.login(email, password);

        if (token != null) {
            // Récupération des infos utilisateur après authentification
            User user = authService.getUserByEmail(email); // Assure-toi que cette méthode existe dans AuthService

            Map<String, Object> response = Map.of(
                    "token", token,
                    "role", user.getRoles(),
                    "user", Map.of(
                            "id", user.getId(),
                            "nom_prenom", user.getNom_prenom(),
                            "email", user.getEmail(),
                            "poste", user.getPoste(),
                            "roles", user.getRoles(),
                            "ville", user.getVille(),
                            "adresse", user.getAdresse(),
                            "descriptions", user.getDescriptions()
                    )
            );

            return ResponseEntity.ok(response);
        } else {
            // Authentification échouée
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Collections.singletonMap("error", "Identifiants incorrects"));
        }
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> register(@RequestBody User user) {
        authService.register(user);
        return ResponseEntity.ok(Collections.singletonMap("message", "Utilisateur enregistré !"));
    }

    @GetMapping("/test")
    public Map<String, String> test() {
        return Collections.singletonMap("message", "API Auth fonctionne !");
    }
}
