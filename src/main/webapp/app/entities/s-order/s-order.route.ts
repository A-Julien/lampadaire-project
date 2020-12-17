import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ISOrder, SOrder } from 'app/shared/model/s-order.model';
import { SOrderService } from './s-order.service';
import { SOrderComponent } from './s-order.component';
import { SOrderDetailComponent } from './s-order-detail.component';
import { SOrderUpdateComponent } from './s-order-update.component';

@Injectable({ providedIn: 'root' })
export class SOrderResolve implements Resolve<ISOrder> {
  constructor(private service: SOrderService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ISOrder> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((sOrder: HttpResponse<SOrder>) => {
          if (sOrder.body) {
            return of(sOrder.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new SOrder());
  }
}

export const sOrderRoute: Routes = [
  {
    path: '',
    component: SOrderComponent,
    data: {
      authorities: [Authority.USER],
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: SOrderDetailComponent,
    resolve: {
      sOrder: SOrderResolve,
    },
    data: {
      authorities: [Authority.USER],
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: SOrderUpdateComponent,
    resolve: {
      sOrder: SOrderResolve,
    },
    data: {
      authorities: [Authority.USER],
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: SOrderUpdateComponent,
    resolve: {
      sOrder: SOrderResolve,
    },
    data: {
      authorities: [Authority.USER],
    },
    canActivate: [UserRouteAccessService],
  },
];
