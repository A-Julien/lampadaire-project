import { Route } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ProfilComponent } from './profil.component';
import { Authority } from 'app/shared/constants/authority.constants';

export const profilRoute: Route = {
  path: 'profil',
  component: ProfilComponent,
  data: {
    authorities: [Authority.USER],
  },
  canActivate: [UserRouteAccessService],
};
