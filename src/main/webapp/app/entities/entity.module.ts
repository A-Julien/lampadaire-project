import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'streetlamp',
        loadChildren: () => import('./streetlamp/streetlamp.module').then(m => m.LampaderumStreetlampModule),
      },
      {
        path: 'roworder',
        loadChildren: () => import('./roworder/roworder.module').then(m => m.LampaderumRoworderModule),
      },
      {
        path: 's-order',
        loadChildren: () => import('./s-order/s-order.module').then(m => m.LampaderumSOrderModule),
      },
      {
        path: 'application-user',
        loadChildren: () => import('./application-user/application-user.module').then(m => m.LampaderumApplicationUserModule),
      },
      {
        path: 'creditcard',
        loadChildren: () => import('./creditcard/creditcard.module').then(m => m.LampaderumCreditcardModule),
      },
      {
        path: 'rowcart',
        loadChildren: () => import('./rowcart/rowcart.module').then(m => m.LampaderumRowcartModule),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class LampaderumEntityModule {}
