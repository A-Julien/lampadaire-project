import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IRowcart, Rowcart } from 'app/shared/model/rowcart.model';
import { RowcartService } from './rowcart.service';
import { RowcartComponent } from './rowcart.component';
import { RowcartDetailComponent } from './rowcart-detail.component';
import { RowcartUpdateComponent } from './rowcart-update.component';

@Injectable({ providedIn: 'root' })
export class RowcartResolve implements Resolve<IRowcart> {
  constructor(private service: RowcartService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IRowcart> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((rowcart: HttpResponse<Rowcart>) => {
          if (rowcart.body) {
            return of(rowcart.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Rowcart());
  }
}

export const rowcartRoute: Routes = [
  {
    path: '',
    component: RowcartComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'lampaderumApp.rowcart.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: RowcartDetailComponent,
    resolve: {
      rowcart: RowcartResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'lampaderumApp.rowcart.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: RowcartUpdateComponent,
    resolve: {
      rowcart: RowcartResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'lampaderumApp.rowcart.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: RowcartUpdateComponent,
    resolve: {
      rowcart: RowcartResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'lampaderumApp.rowcart.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
