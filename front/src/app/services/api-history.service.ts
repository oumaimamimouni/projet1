import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ApiHistoryService {

  private apiUrl = 'http://localhost:8080/api/history';

  constructor(private http: HttpClient) { }

  getHistory(): Observable<any> {
    const token = localStorage.getItem('token');
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`
    });
    console.log(token) 
    return this.http.get(this.apiUrl, { headers });
  }
}
