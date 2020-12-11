import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { LampaderumSharedModule } from 'app/shared/shared.module';
import { RoworderComponent } from './roworder.component';
import { RoworderDetailComponent } from './roworder-detail.component';
import { RoworderUpdateComponent } from './roworder-update.component';
import { RoworderDeleteDialogComponent } from './roworder-delete-dialog.component';
import { roworderRoute } from './roworder.route';

@NgModule({
  imports: [LampaderumSharedModule, RouterModule.forChild(roworderRoute)],
  declarations: [RoworderComponent, RoworderDetailComponent, RoworderUpdateComponent, RoworderDeleteDialogComponent],
  entryComponents: [RoworderDeleteDialogComponent],
})
export class LampaderumRoworderModule {}
