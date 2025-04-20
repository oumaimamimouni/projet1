import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

declare interface RouteInfo {
  path: string;
  title: string;
  icon: string;
  class: string;
}
export const ROUTES: RouteInfo[] = [
  { path: '/dashboard', title: 'Dashboard', icon: 'design_app', class: '' },
  { path: '/models', title: 'api', icon: 'design_bullet-list-67', class: '' },
  { path: '/log', title: 'log', icon: 'text_caps-small', class: '' },
  { path: '/admin/liste-des-utilisateurs', title: 'Liste des utilisateurs', icon: 'design_bullet-list-67', class: '' },
  { path: '/notifications', title: 'Notifications', icon: 'ui-1_bell-53', class: '' },
  { path: '/user-profile', title: 'User Profile', icon: 'users_single-02', class: '' },
  { path: '/maps', title: 'Maps', icon: 'location_map-big', class: '' },
  { path: '/login', title: 'Déconnexion', icon: 'ui-1_lock-circle-open', class: 'active active-pro' }
];

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.css']
})
export class SidebarComponent implements OnInit {
  menuItems: any[] = [];
  userRole: string = ''; 

  private adminRoutes = [
    '/admin/liste-des-utilisateurs',
    '/notifications'
  ];

  constructor(private router: Router) {}

  ngOnInit() {
    this.userRole = localStorage.getItem('userRole') || '';
    console.log('Role utilisateur:', this.userRole); // ✅ debug

    // On filtre le menu en fonction du rôle
    this.menuItems = ROUTES.filter(menuItem => {
      if (this.userRole !== 'ADMIN') {
        // Exclure les routes réservées à l'ADMIN pour l'utilisateur
        return !this.adminRoutes.includes(menuItem.path);
      }
      return true; // ADMIN peut voir toutes les pages
    });
  }

  isMobileMenu() {
    return window.innerWidth <= 991;
  }

  logout() {
    // 🧹 Nettoyage : tokens, sessions, etc.
    localStorage.clear(); // ou sessionStorage.clear()

    // 🔁 Redirection vers la page de login
    this.router.navigate(['/connexion']);
  }
}
