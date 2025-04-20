import { Component, OnInit } from '@angular/core';
import { UtilisateurService } from '../services/utilisateur.service';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css']
})
export class UserProfileComponent implements OnInit {
  utilisateurConnecte: any = {};
  imageSrc: string | ArrayBuffer | null = null;
  selectedFile: File | null = null;

  prenom: string = '';
  nom: string = '';
  password: string = '';

  constructor(private utilisateurService: UtilisateurService) {}

  ngOnInit(): void {
    this.utilisateurConnecte = this.utilisateurService.getUtilisateurConnecte();

    if (this.utilisateurConnecte?.nom_prenom) {
      const [prenom, nom] = this.utilisateurConnecte.nom_prenom.split(' ');
      this.prenom = prenom || '';
      this.nom = nom || '';
    }

    if (this.utilisateurConnecte?.email) {
      this.utilisateurService.getImageByEmail(this.utilisateurConnecte.email).subscribe(
        blob => {
          const reader = new FileReader();
          reader.onload = () => {
            this.imageSrc = reader.result;
          };
          reader.readAsDataURL(blob);
        },
        err => {
          console.warn('Image non trouvée pour cet utilisateur.');
        }
      );
    }

    if (this.utilisateurConnecte?.nom_prenom) {
      console.log('Utilisateur connecté :', this.utilisateurConnecte.nom_prenom);
    } else {
      console.log('Aucun utilisateur connecté');
    }
  }

  onFileSelected(event: any): void {
    const file = event.target.files[0];
    if (file) {
      const reader = new FileReader();
      reader.onload = () => {
        this.imageSrc = reader.result;
        this.selectedFile = file;
      };
      reader.readAsDataURL(file);
    }
  }

  onSubmit(): void {
    if (!this.prenom || !this.nom || !this.utilisateurConnecte.email) {
      alert('Veuillez remplir tous les champs obligatoires.');
      return;
    }

    // Mettre à jour nom_prenom
    this.utilisateurConnecte.nom_prenom = `${this.prenom} ${this.nom}`.trim();

    // Ajoute le mot de passe si l’utilisateur l’a rempli
    if (this.password && this.password.trim() !== '') {
      this.utilisateurConnecte.password = this.password;
    } else {
      delete this.utilisateurConnecte.password;
    }

    const formData = new FormData();

    if (this.selectedFile) {
      formData.append('image', this.selectedFile);
    }

    formData.append('utilisateur', JSON.stringify(this.utilisateurConnecte));

    this.utilisateurService.updateUtilisateurWithImage(formData).subscribe(
      res => {
        alert('Profil mis à jour avec succès!');
      },
      err => {
        console.error('Erreur lors de la mise à jour', err);
        alert('Erreur lors de la mise à jour. Veuillez réessayer.');
      }
    );
  }
}
