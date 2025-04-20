package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    // Injection des dépendances via le constructeur
    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    /**
     * 🔐 Connexion : retourne uniquement le token si identifiants valides
     */
    public String login(String email, String password) {
        Optional<User> userOpt = userRepository.findByEmail(email);

        if (userOpt.isPresent()) {
            User user = userOpt.get();

            if (passwordEncoder.matches(password, user.getPassword())) {
                return jwtUtil.generateToken(email); // Peut être ajusté pour inclure l'ID ou d'autres infos
            }
        }

        return null;
    }

    /**
     * 🔍 Récupère les infos d’un utilisateur par email
     */
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

    /**
     * 📝 Enregistrement d’un nouvel utilisateur
     */
    public String register(User user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        System.out.println("Mot de passe encodé : " + encodedPassword); // Pour debug
        user.setPassword(encodedPassword);
        user.setRoles("USER"); // Rôle par défaut
        userRepository.save(user);
        return "Utilisateur enregistré !";
    }
}
