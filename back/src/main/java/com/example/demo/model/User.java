package com.example.demo.model;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nom_prenom;

    @Column(nullable = false)
    private String poste;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String roles; // ex : "ADMIN", "USER", etc.

    @Column(nullable = false)
    private String ville;

    @Column(nullable = false)
    private String adresse;

    @Column(nullable = false)
    private String descriptions;


    @Lob
    private byte[] image; // image stockée en base de données


    public User() {}

    // Constructeur avec 3 paramètres
    public User(Long id,String email, String password,String poste,String nom_prenom, String roles,byte[] image ,String ville,String adresse,String descriptions) {
        this.email = email;
        this.id = id;
        this.nom_prenom = nom_prenom;
        this. poste=  poste;
        this.password = password;
        this.ville = ville;
        this.adresse = adresse;
        this.descriptions = descriptions;
        this.roles = (roles != null) ? roles : "USER";
        this.image = image;
    }

    // Constructeur avec 2 paramètres (corrige l'erreur)
    public User(String email, String password) {
        this.email = email;
        this.password = password;
        this.roles = "USER";
    }

    // Getters et setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNom_prenom() { return nom_prenom; }
    public void setNom_prenom(String nom_prenom) { this.nom_prenom = nom_prenom; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }


    public String getPoste() { return poste; }
    public void setPoste(String poste) { this.poste = poste; }

    public String getVille() { return ville; }
    public void setVille(String ville) { this.ville = ville; }

    public String getAdresse() { return adresse; }
    public void setAdresse(String adresse) { this.adresse =adresse; }

    public String getDescriptions() { return descriptions; }
    public void setDescriptions(String descriptions) { this.descriptions = descriptions; }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", roles=" + roles +
                '}';
    }
}