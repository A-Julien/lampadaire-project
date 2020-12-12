import { Routes } from '@angular/router';

import { HomeComponent } from './home.component';
import { DetailPageComponent } from 'app/core/detail-page/detail-page.component';
import { StreetlampResolve } from 'app/entities/streetlamp/streetlamp.route';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';

export const HOME_ROUTE: Routes = [
  {
  path: '',
  component: HomeComponent,
  data: {
    authorities: [],
    pageTitle: 'home.title',
    }
  },
  {
    path: ':id/view',
    component: DetailPageComponent,
    resolve: {
      streetlamp: StreetlampResolve
    },
    data: {
      authorities: [],
      pageTitle: 'home.title'
    },
    canActivate: [UserRouteAccessService]
  }
]
