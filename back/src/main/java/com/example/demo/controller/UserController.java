package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:4200") // Autorise les appels depuis Angular
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Endpoint GET pour récupérer tous les utilisateurs
    @GetMapping
    public List<User> getUsers() {
        return userService.getAllUsers();


    }
    @PostMapping("/update")
    public ResponseEntity<?> updateUtilisateur(@RequestBody User updatedData) {
        Optional<User> existingUserOpt = userService.findByEmail(updatedData.getEmail());

        if (existingUserOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        User existingUser = existingUserOpt.get();

        // Mise à jour des champs
        existingUser.setNom_prenom(updatedData.getNom_prenom());
        existingUser.setPoste(updatedData.getPoste());
        existingUser.setVille(updatedData.getVille());
        existingUser.setAdresse(updatedData.getAdresse());
        existingUser.setDescriptions(updatedData.getDescriptions());

        if (updatedData.getPassword() != null && !updatedData.getPassword().isEmpty()) {
            existingUser.setPassword(userService.encodePassword(updatedData.getPassword()));
        }

        User savedUser = userService.saveUser(existingUser);
        return ResponseEntity.ok(savedUser);
    }

    @PostMapping("/update-with-image")
    public ResponseEntity<?> updateProfileWithImage(
            @RequestParam(value = "image", required = false) MultipartFile image, // image est optionnelle
            @RequestParam("utilisateur") String userJson
    ) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        User updatedData = mapper.readValue(userJson, User.class);  // Données utilisateur envoyées

        Optional<User> existingUserOpt = userService.findByEmail(updatedData.getEmail());

        if (existingUserOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        User existingUser = existingUserOpt.get();

        // Mise à jour des champs modifiables, même si l'image n'est pas envoyée
        if (updatedData.getNom_prenom() != null && !updatedData.getNom_prenom().isEmpty()) {
            existingUser.setNom_prenom(updatedData.getNom_prenom());
        }
        if (updatedData.getPoste() != null && !updatedData.getPoste().isEmpty()) {
            existingUser.setPoste(updatedData.getPoste());
        }
        if (updatedData.getVille() != null && !updatedData.getVille().isEmpty()) {
            existingUser.setVille(updatedData.getVille());
        }
        if (updatedData.getAdresse() != null && !updatedData.getAdresse().isEmpty()) {
            existingUser.setAdresse(updatedData.getAdresse());
        }
        if (updatedData.getDescriptions() != null && !updatedData.getDescriptions().isEmpty()) {
            existingUser.setDescriptions(updatedData.getDescriptions());
        }

        // Mise à jour du mot de passe (si spécifié)
        if (updatedData.getPassword() != null && !updatedData.getPassword().isEmpty()) {
            existingUser.setPassword(userService.encodePassword(updatedData.getPassword()));
        }

        // Mise à jour de l'email (si spécifié et si l'email est modifié)
        if (updatedData.getEmail() != null && !updatedData.getEmail().isEmpty() &&
                !updatedData.getEmail().equals(existingUser.getEmail())) {
            existingUser.setEmail(updatedData.getEmail());
        }

        // Mise à jour de l'image (si spécifiée)
        if (image != null && !image.isEmpty()) {
            existingUser.setImage(image.getBytes());
        }

        // Sauvegarder l'utilisateur mis à jour
        User savedUser = userService.saveUser(existingUser);
        return ResponseEntity.ok(savedUser);
    }


    @GetMapping("/image/{email}")
    public ResponseEntity<byte[]> getImageByEmail(@PathVariable String email) {
        Optional<User> userOpt = userService.findByEmail(email);
        if (userOpt.isPresent() && userOpt.get().getImage() != null) {
            byte[] imageBytes = userOpt.get().getImage();
            return ResponseEntity
                    .ok()
                    .header("Content-Type", "image/jpeg")
                    .body(imageBytes);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        // Encodage du mot de passe si nécessaire
        if (user.getPassword() != null && !user.getPassword().isEmpty()) {
            user.setPassword(userService.encodePassword(user.getPassword()));
        }

        User createdUser = userService.saveUser(user);
        return ResponseEntity.ok(createdUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        Optional<User> user = userService.findById(id);
        if (user.isPresent()) {
            userService.deleteUser(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}
