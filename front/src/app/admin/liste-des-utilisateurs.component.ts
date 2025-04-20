import { Component, OnInit } from '@angular/core';
import { UtilisateurService } from '../services/utilisateur.service';
import { Router } from '@angular/router';
import { MatDialog } from '@angular/material/dialog';
import { AjouterUtilisateurDialogComponent } from '../ajouter-utilisateur-dialog/ajouter-utilisateur-dialog.component';

@Component({
  selector: 'app-liste-utilisateurs',
  templateUrl: './liste-des-utilisateurs.component.html',
  styleUrls: ['./liste-des-utilisateurs.component.scss']
})
export class ListeDesUtilisateursComponent implements OnInit {
  utilisateurs: any[] = [];
  utilisateursFiltres: any[] = [];
  errorMessage: string = '';
  isLoading: boolean = true;
  searchTerm: string = '';

  constructor(
    private utilisateurService: UtilisateurService,
    private router: Router,
    private dialog: MatDialog
  ) {}

  ngOnInit() {
    this.chargerUtilisateurs();
  }

  chargerUtilisateurs(): void {
    this.isLoading = true;
    this.utilisateurService.getUtilisateurs().subscribe(
      data => {
        this.utilisateurs = data;
        this.utilisateursFiltres = data;
        this.isLoading = false;
      },
      error => {
        this.errorMessage = 'Une erreur est survenue lors du chargement des utilisateurs.';
        this.isLoading = false;
      }
    );
  }

  ouvrirFormulaireAjout(): void {
    const dialogRef = this.dialog.open(AjouterUtilisateurDialogComponent, {
      width: '400px'
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result === 'refresh') {
        this.chargerUtilisateurs();
      }
    });
  }

  supprimerUtilisateur(utilisateur: any) {
    if (confirm("Êtes-vous sûr de vouloir supprimer cet utilisateur ?")) {
      this.utilisateurService.supprimerUtilisateur(utilisateur.id).subscribe(() => {
        alert("✅ Utilisateur supprimé avec succès !");
        this.chargerUtilisateurs(); // 👈 refresh depuis l'API
      }, err => {
        alert("❌ Erreur lors de la suppression !");
        console.error(err);
      });
    }
  }
  

  filtrerUtilisateurs(): void {
    if (!this.searchTerm) {
      this.utilisateursFiltres = this.utilisateurs;
    } else {
      const term = this.searchTerm.toLowerCase();
      this.utilisateursFiltres = this.utilisateurs.filter(u =>
        u.nom_prenom.toLowerCase().includes(term)
      );
    }
  }
}
