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
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class LampaderumEntityModule {}
