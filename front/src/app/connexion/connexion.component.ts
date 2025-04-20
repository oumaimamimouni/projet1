import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../services/auth.service';

@Component({
  selector: 'app-connexion',
  templateUrl: './connexion.component.html',
  styleUrls: ['./connexion.component.scss']
})
export class ConnexionComponent {
  email: string = '';
  password: string = '';
  errorMessage: string = '';  // Message d'erreur global
  emailError: string = '';    // Message d'erreur spécifique à l'email
  passwordError: string = ''; // Message d'erreur spécifique au mot de passe
  isLoginAttempted: boolean = false; // Pour savoir si une tentative a échoué

  constructor(private authService: AuthService, private router: Router) {}

  // Méthode pour valider l'email et le mot de passe avant de les envoyer
  onLogin() {
    // Réinitialisation des erreurs
    this.emailError = '';
    this.passwordError = '';
    this.errorMessage = '';

    // Validation de l'email
    if (!this.email) {
      this.emailError = 'Email est requis.';
    } else if (!this.validateEmail(this.email)) {
      this.emailError = 'Email invalide.';
    }

    // Validation du mot de passe
    if (!this.password) {
      this.passwordError = 'Mot de passe est requis.';
    } 

    // Si l'un des champs a une erreur, ne pas soumettre le formulaire
    if (this.emailError || this.passwordError) {
      return;
    }

    // Tentative de connexion
    this.authService.login(this.email, this.password).subscribe(
      response => {
        console.log('Réponse backend :', response); // 👈 Ajoute ça pour vérifier
    
        if (response.token) {
          localStorage.setItem('token', response.token);
    
          // Stocke le rôle s’il existe
          if (response.role) {
            localStorage.setItem('userRole', response.role);
            console.log('Rôle utilisateur:', response.role);
          } else {
            console.warn('Rôle non présent dans la réponse !');
          }
    
          this.router.navigate(['/dashboard']);
        }
      },
      error => {
        if (error.status === 401) {
          // Si l'authentification échoue
          this.errorMessage = 'Email ou mot de passe incorrect';
        } else {
          this.errorMessage = 'Une erreur est survenue. Veuillez réessayer plus tard.';
        }

        // L'utilisateur peut essayer à nouveau immédiatement après un échec
      }
    );
  }

  // Fonction pour valider l'email avec une expression régulière
  validateEmail(email: string): boolean {
    const emailPattern = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
    return emailPattern.test(email);
  }
}
