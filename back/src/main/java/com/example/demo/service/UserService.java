package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Collections;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Optional<User> registerUser(String email, String password) {
        // Vérifier si l'utilisateur existe déjà
        if (userRepository.findByEmail(email).isPresent()) {
            return Optional.empty(); // Retourner un Optional vide si l'email est déjà utilisé
        }

        // Hacher le mot de passe
        String encodedPassword = passwordEncoder.encode(password);

        // Créer et sauvegarder l'utilisateur
        User user = new User(email, encodedPassword);
        user.setRoles("USER");  // Ajout du rôle USER si nécessaire
        userRepository.save(user);

        return Optional.of(user);
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public String encodePassword(String rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

}