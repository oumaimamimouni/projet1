import { Component, OnInit } from '@angular/core';
import { ApiHistoryService } from '../services/api-history.service';
import { MatDialog } from '@angular/material/dialog';
import { ApiDetailsComponent } from '../models/api-details.component'; // adapte le chemin selon ton projet


@Component({
  selector: 'app-api-list',
  templateUrl: './api-list.component.html',
  styleUrls: ['./api-list.component.css']
})
export class ApiListComponent implements OnInit {
  apiHistory: any[] = [];

  constructor(
    private apiHistoryService: ApiHistoryService,
    private dialog: MatDialog 
  ) {}
  

  ngOnInit(): void {
    // Utiliser la méthode correcte du service
    this.apiHistoryService.getHistory().subscribe((data) => {
      this.apiHistory = data;
    });
  }
  voirDetails(api: any): void {
    this.dialog.open(ApiDetailsComponent, {
      width: '400px',
      data: api
    });
}}
