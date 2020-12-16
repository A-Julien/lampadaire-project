import { Routes } from '@angular/router';

import { NavbarComponent } from './navbar.component';
import { CatalogueComponent } from 'app/catalogue/catalogue.component';

export const navbarRoute: Routes = [
  {
    path: '',
    component: NavbarComponent,
    outlet: 'navbar',
  },
  {
    path: 'catalogue',
    component: CatalogueComponent,
  },
];
