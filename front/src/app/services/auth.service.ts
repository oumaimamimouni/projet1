// auth.service.ts
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { tap } from 'rxjs/operators';

import { jwtDecode } from 'jwt-decode'; // Importation correcte

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private apiUrl = 'http://localhost:8080/api/auth';

  constructor(private http: HttpClient) {}

  login(email: string, password: string): Observable<any> {
    return this.http.post<any>(`${this.apiUrl}/login`, { email, password }).pipe(
      tap(response => {
        if (response && response.token) {
          localStorage.setItem('token', response.token);
          localStorage.setItem('utilisateurConnecte', JSON.stringify(response.user));
        } else {
          console.warn('Aucun utilisateur dans la réponse !');
          localStorage.removeItem('utilisateurConnecte');
        }
      })
    );
  }
  

  register(email: string, password: string): Observable<any> {
    return this.http.post(`${this.apiUrl}/register`, { email, password });
  }

  getToken(): string | null {
    return localStorage.getItem('token');
  }

  getUserFromToken(): any {
    const token = this.getToken();
    if (!token) return null;
    try {
      return jwtDecode(token); // Correct usage of jwtDecode
    } catch (e) {
      console.error('Erreur de décodage du token', e);
      return null;
    }
  }

  logout(): void {
    localStorage.removeItem('token');
    localStorage.removeItem('utilisateurConnecte'); // Supprimez aussi les informations de l'utilisateur
  }
}
