import { Routes } from '@angular/router';

import { DashboardComponent } from '../../dashboard/dashboard.component';
import { UserProfileComponent } from '../../user-profile/user-profile.component';
import { LogsComponent } from '../../log/log.component';
import { ApiListComponent} from '../../models/api-list.component';
import { MapsComponent } from '../../maps/maps.component';
import { NotificationsComponent } from '../../notifications/notifications.component';
import { ListeDesUtilisateursComponent } from '../../admin/liste-des-utilisateurs.component';
export const AdminLayoutRoutes: Routes = [
    { path: 'dashboard',      component: DashboardComponent },
    { path: 'user-profile',   component: UserProfileComponent },
    { path: 'log',            component: LogsComponent },
    { path: 'models',       component: ApiListComponent },
    { path: 'maps',           component: MapsComponent },
    { path: 'notifications',  component: NotificationsComponent },
    { path: 'admin/liste-des-utilisateurs', component: ListeDesUtilisateursComponent},
    
];
