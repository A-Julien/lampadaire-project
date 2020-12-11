import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { LampaderumSharedModule } from 'app/shared/shared.module';
import { SOrderComponent } from './s-order.component';
import { SOrderDetailComponent } from './s-order-detail.component';
import { SOrderUpdateComponent } from './s-order-update.component';
import { SOrderDeleteDialogComponent } from './s-order-delete-dialog.component';
import { sOrderRoute } from './s-order.route';

@NgModule({
  imports: [LampaderumSharedModule, RouterModule.forChild(sOrderRoute)],
  declarations: [SOrderComponent, SOrderDetailComponent, SOrderUpdateComponent, SOrderDeleteDialogComponent],
  entryComponents: [SOrderDeleteDialogComponent],
})
export class LampaderumSOrderModule {}
