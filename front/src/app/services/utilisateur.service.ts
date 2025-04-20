// utilisateur.service.ts
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { throwError } from 'rxjs';
import { Observable } from 'rxjs';
import { tap } from 'rxjs/operators';
import { catchError } from 'rxjs/operators';
import { utilisateur } from '../models/utilisateur.model';
import { HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class UtilisateurService {
  private apiUrl = 'http://localhost:8080/api/users';

  constructor(private http: HttpClient) {}

  getUtilisateurs(): Observable<any[]> {
    return this.http.get<any[]>(this.apiUrl).pipe(
      tap(data => console.log('Réponse API:', data)),
      catchError(error => {
        console.error('Erreur lors de la récupération des utilisateurs', error);
        return throwError(() => error);
      })
    );
  }

  supprimerUtilisateur(id: number) {
    return this.http.delete(`${this.apiUrl}/${id}`);
  }

  ajouterUtilisateur(utilisateur: any) {
    const token = localStorage.getItem('token'); // ou sessionStorage selon ton cas
    const headers = new HttpHeaders().set('Authorization', 'Bearer ' + token);
    return this.http.post(this.apiUrl, utilisateur, { headers });
  }

  getUtilisateurConnecte(): utilisateur | null {
    const userData = localStorage.getItem('utilisateurConnecte');
  
    try {
      if (userData && userData !== 'undefined') {
        const parsedData = JSON.parse(userData);
        if (parsedData && parsedData.id) {  // Vérifie que l'objet contient des données valides
          return parsedData;
        }
      }
    } catch (error) {
      console.error("Erreur parsing JSON:", error);
    }
  
    console.log('Aucun utilisateur connecté');
    return null;
  }

  updateUtilisateur(utilisateur: any): Observable<any> {
    return this.http.put(`${this.apiUrl}/${utilisateur.id}`, utilisateur);
  }
  
  // Méthode pour mettre à jour l'utilisateur avec ou sans image
  updateUtilisateurWithImage(formData: FormData) {
    return this.http.post(`${this.apiUrl}/update-with-image`, formData);
  }
  getImageByEmail(email: string): Observable<Blob> {
    return this.http.get(`http://localhost:8080/api/users/image/${email}`, {
      responseType: 'blob'
    });
  }
    

}
