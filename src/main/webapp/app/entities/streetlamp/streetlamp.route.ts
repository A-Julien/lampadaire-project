import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IStreetlamp, Streetlamp } from 'app/shared/model/streetlamp.model';
import { StreetlampService } from './streetlamp.service';
import { StreetlampComponent } from './streetlamp.component';
import { StreetlampDetailComponent } from './streetlamp-detail.component';
import { StreetlampUpdateComponent } from './streetlamp-update.component';

@Injectable({ providedIn: 'root' })
export class StreetlampResolve implements Resolve<IStreetlamp> {
  constructor(private service: StreetlampService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IStreetlamp> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((streetlamp: HttpResponse<Streetlamp>) => {
          if (streetlamp.body) {
            return of(streetlamp.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Streetlamp());
  }
}

export const streetlampRoute: Routes = [
  {
    path: '',
    component: StreetlampComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'lampaderumApp.streetlamp.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: StreetlampDetailComponent,
    resolve: {
      streetlamp: StreetlampResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'lampaderumApp.streetlamp.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: StreetlampUpdateComponent,
    resolve: {
      streetlamp: StreetlampResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'lampaderumApp.streetlamp.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: StreetlampUpdateComponent,
    resolve: {
      streetlamp: StreetlampResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'lampaderumApp.streetlamp.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
