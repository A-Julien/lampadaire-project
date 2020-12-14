import { Route } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { OrderComponent } from './order.component';
import { Authority } from 'app/shared/constants/authority.constants';

export const orderRoute: Route = {
  path: 'order',
  component: OrderComponent,
  data: {
    authorities: [Authority.USER],
    pageTitle: 'global.menu.account.profil.order',
  },
  canActivate: [UserRouteAccessService],
};
