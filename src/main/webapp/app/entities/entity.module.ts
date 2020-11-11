import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'streetlamp',
        loadChildren: () => import('./streetlamp/streetlamp.module').then(m => m.LampaderumStreetlampModule),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class LampaderumEntityModule {}
