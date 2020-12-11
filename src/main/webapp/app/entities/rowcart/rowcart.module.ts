import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { LampaderumSharedModule } from 'app/shared/shared.module';
import { RowcartComponent } from './rowcart.component';
import { RowcartDetailComponent } from './rowcart-detail.component';
import { RowcartUpdateComponent } from './rowcart-update.component';
import { RowcartDeleteDialogComponent } from './rowcart-delete-dialog.component';
import { rowcartRoute } from './rowcart.route';

@NgModule({
  imports: [LampaderumSharedModule, RouterModule.forChild(rowcartRoute)],
  declarations: [RowcartComponent, RowcartDetailComponent, RowcartUpdateComponent, RowcartDeleteDialogComponent],
  entryComponents: [RowcartDeleteDialogComponent],
})
export class LampaderumRowcartModule {}
