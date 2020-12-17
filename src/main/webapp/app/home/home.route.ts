import { Routes } from '@angular/router';

import { HomeComponent } from './home.component';
import { DetailPageComponent } from 'app/core/detail-page/detail-page.component';
import { StreetlampResolve } from 'app/entities/streetlamp/streetlamp.route';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { PayRecapComponent } from 'app/core/pay-recap/pay-recap.component';
import { User } from 'app/core/user/user.model';
import { PayFormComponent } from 'app/core/pay-form/pay-form.component';
import { CatalogueComponent } from 'app/catalogue/catalogue.component';

export const HOME_ROUTE: Routes = [
  {
    path: '',
    component: HomeComponent,
    data: {
      authorities: [],
      title: 'Lampaderum',
    },
  },
  {
    path: 'catalogue',
    component: CatalogueComponent,
    data: {
      authorities: [],
    },
  },
  {
    path: ':id/view',
    component: DetailPageComponent,
    resolve: {
      streetlamp: StreetlampResolve,
    },
    data: {
      authorities: [],
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'recap',
    component: PayRecapComponent,
    data: {
      authorities: [],
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'pay-form',
    component: PayFormComponent,
    data: {
      authorities: [User],
    },
    // canActivate: [UserRouteAccessService],
  },
];
