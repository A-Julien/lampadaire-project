import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ICartpersi, Cartpersi } from 'app/shared/model/cartpersi.model';
import { CartpersiService } from './cartpersi.service';
import { CartpersiComponent } from './cartpersi.component';
import { CartpersiDetailComponent } from './cartpersi-detail.component';
import { CartpersiUpdateComponent } from './cartpersi-update.component';

@Injectable({ providedIn: 'root' })
export class CartpersiResolve implements Resolve<ICartpersi> {
  constructor(private service: CartpersiService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ICartpersi> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((cartpersi: HttpResponse<Cartpersi>) => {
          if (cartpersi.body) {
            return of(cartpersi.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Cartpersi());
  }
}

export const cartpersiRoute: Routes = [
  {
    path: '',
    component: CartpersiComponent,
    data: {
      authorities: [Authority.USER],
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: CartpersiDetailComponent,
    resolve: {
      cartpersi: CartpersiResolve,
    },
    data: {
      authorities: [Authority.USER],
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: CartpersiUpdateComponent,
    resolve: {
      cartpersi: CartpersiResolve,
    },
    data: {
      authorities: [Authority.USER],
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: CartpersiUpdateComponent,
    resolve: {
      cartpersi: CartpersiResolve,
    },
    data: {
      authorities: [Authority.USER],
    },
    canActivate: [UserRouteAccessService],
  },
];
