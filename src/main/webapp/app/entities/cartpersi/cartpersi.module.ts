import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { LampaderumSharedModule } from 'app/shared/shared.module';
import { CartpersiComponent } from './cartpersi.component';
import { CartpersiDetailComponent } from './cartpersi-detail.component';
import { CartpersiUpdateComponent } from './cartpersi-update.component';
import { CartpersiDeleteDialogComponent } from './cartpersi-delete-dialog.component';
import { cartpersiRoute } from './cartpersi.route';

@NgModule({
  imports: [LampaderumSharedModule, RouterModule.forChild(cartpersiRoute)],
  declarations: [CartpersiComponent, CartpersiDetailComponent, CartpersiUpdateComponent, CartpersiDeleteDialogComponent],
  entryComponents: [CartpersiDeleteDialogComponent],
})
export class LampaderumCartpersiModule {}
