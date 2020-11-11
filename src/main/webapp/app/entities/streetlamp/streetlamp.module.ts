import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { LampaderumSharedModule } from 'app/shared/shared.module';
import { StreetlampComponent } from './streetlamp.component';
import { StreetlampDetailComponent } from './streetlamp-detail.component';
import { StreetlampUpdateComponent } from './streetlamp-update.component';
import { StreetlampDeleteDialogComponent } from './streetlamp-delete-dialog.component';
import { streetlampRoute } from './streetlamp.route';

@NgModule({
  imports: [LampaderumSharedModule, RouterModule.forChild(streetlampRoute)],
  declarations: [StreetlampComponent, StreetlampDetailComponent, StreetlampUpdateComponent, StreetlampDeleteDialogComponent],
  entryComponents: [StreetlampDeleteDialogComponent],
})
export class LampaderumStreetlampModule {}
