import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef } from '@angular/material/dialog';
import { UtilisateurService } from '../services/utilisateur.service';

@Component({
  selector: 'app-ajouter-utilisateur-dialog',
  templateUrl: './ajouter-utilisateur-dialog.component.html',
  styleUrls: ['./ajouter-utilisateur-dialog.component.css']
})
export class AjouterUtilisateurDialogComponent {
  form: FormGroup;

  constructor(
    private fb: FormBuilder,
    private utilisateurService: UtilisateurService,
    private dialogRef: MatDialogRef<AjouterUtilisateurDialogComponent>
  ) {
    this.form = this.fb.group({
      nom_prenom: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      password: ['', Validators.required],
      role: ['', Validators.required],
      poste: ['']
    });
  }

  ajouter() {
    if (this.form.valid) {
      const data = {
        ...this.form.value,
        roles: this.form.value.role // <-- juste une chaîne
      };
      delete data.role; // facultatif, pour éviter un doublon inutile
  
      this.utilisateurService.ajouterUtilisateur(data).subscribe(() => {
        alert("✅ Utilisateur ajouté avec succès !");
        this.dialogRef.close('refresh');
      }, err => {
        alert("❌ Erreur lors de l'ajout");
        console.error(err);
      });
    }
  }
  
  
}
