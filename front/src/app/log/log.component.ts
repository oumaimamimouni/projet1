import { Component } from '@angular/core';

@Component({
  selector: 'app-logs',
  templateUrl: './log.component.html',
  styleUrls: ['./log.component.css']
})
export class LogsComponent {
  logs = [
    { type: 'Succès', message: 'Opération réussie', time: '12:30', icon: '✔️', color: 'success' },
    { type: 'Erreur', message: 'Échec de connexion', time: '12:45', icon: '❌', color: 'error' },
    { type: 'Info', message: 'Mise à jour disponible', time: '13:00', icon: 'ℹ️', color: 'info' },
    { type: 'Avertissement', message: 'Espace disque faible', time: '14:00', icon: '⚠️', color: 'warning' }
  ];
  logCategories = [
    { type: 'Succès', icon: '✔️', color: 'success', route: '/logs/success' },
    { type: 'Erreur', icon: '❌', color: 'error', route: '/logs/error' },
    { type: 'Info', icon: 'ℹ️', color: 'info', route: '/logs/info' },
    { type: 'Avertissement', icon: '⚠️', color: 'warning', route: '/logs/warning' }
  ];
  

  countLogs(type: string): number {
    return this.logs.filter(log => log.color === type).length;
  }

  navigateToLogs(route: string) {
    console.log('Navigation vers', route);
    // Implémente la navigation avec Router si nécessaire
  }
}
