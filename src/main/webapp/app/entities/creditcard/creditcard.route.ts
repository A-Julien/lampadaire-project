import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ICreditcard, Creditcard } from 'app/shared/model/creditcard.model';
import { CreditcardService } from './creditcard.service';
import { CreditcardComponent } from './creditcard.component';
import { CreditcardDetailComponent } from './creditcard-detail.component';
import { CreditcardUpdateComponent } from './creditcard-update.component';

@Injectable({ providedIn: 'root' })
export class CreditcardResolve implements Resolve<ICreditcard> {
  constructor(private service: CreditcardService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ICreditcard> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((creditcard: HttpResponse<Creditcard>) => {
          if (creditcard.body) {
            return of(creditcard.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Creditcard());
  }
}

export const creditcardRoute: Routes = [
  {
    path: '',
    component: CreditcardComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'lampaderumApp.creditcard.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: CreditcardDetailComponent,
    resolve: {
      creditcard: CreditcardResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'lampaderumApp.creditcard.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: CreditcardUpdateComponent,
    resolve: {
      creditcard: CreditcardResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'lampaderumApp.creditcard.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: CreditcardUpdateComponent,
    resolve: {
      creditcard: CreditcardResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'lampaderumApp.creditcard.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
