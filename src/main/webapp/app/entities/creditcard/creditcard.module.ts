import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { LampaderumSharedModule } from 'app/shared/shared.module';
import { CreditcardComponent } from './creditcard.component';
import { CreditcardDetailComponent } from './creditcard-detail.component';
import { CreditcardUpdateComponent } from './creditcard-update.component';
import { CreditcardDeleteDialogComponent } from './creditcard-delete-dialog.component';
import { creditcardRoute } from './creditcard.route';

@NgModule({
  imports: [LampaderumSharedModule, RouterModule.forChild(creditcardRoute)],
  declarations: [CreditcardComponent, CreditcardDetailComponent, CreditcardUpdateComponent, CreditcardDeleteDialogComponent],
  entryComponents: [CreditcardDeleteDialogComponent],
})
export class LampaderumCreditcardModule {}
