import { Route } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { CreditcardComponent } from './creditcard.component';
import { Authority } from 'app/shared/constants/authority.constants';

export const CreditcardRoute: Route = {
  path: 'creditcards',
  component: CreditcardComponent,
  data: {
    authorities: [Authority.USER],
    pageTitle: 'global.menu.account.profil.creditcard',
  },
  canActivate: [UserRouteAccessService],
};
