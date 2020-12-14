import { Routes } from '@angular/router';

import { HomeComponent } from './home.component';
import { DetailPageComponent } from 'app/core/detail-page/detail-page.component';
import { StreetlampResolve } from 'app/entities/streetlamp/streetlamp.route';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { PayRecapComponent } from 'app/core/pay-recap/pay-recap.component';
import { User } from 'app/core/user/user.model';
import { PayFormComponent } from 'app/core/pay-form/pay-form.component';
import { PayCardComponent } from 'app/core/pay-card/pay-card.component';

export const HOME_ROUTE: Routes = [
  {
    path: '',
    component: HomeComponent,
    data: {
      authorities: [],
      pageTitle: 'home.title',
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
      pageTitle: 'home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'recap',
    component: PayCardComponent,
    data: {
      authorities: [],
      pageTitle: 'home.title',
    },
    children: [
      {
        path: 'payForm',
        component: PayFormComponent,
        data: { authorities: [User] },
      },
    ],
    canActivate: [UserRouteAccessService],
  },
  /*{
    path: 'payForm',
    component: PayFormComponent,
    data: {
      authorities: [User],
      pageTitle: 'home.title',
    },
    canActivate: [UserRouteAccessService],
  }*/
];
