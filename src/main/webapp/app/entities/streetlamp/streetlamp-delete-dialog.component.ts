import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IStreetlamp } from 'app/shared/model/streetlamp.model';
import { StreetlampService } from './streetlamp.service';

@Component({
  templateUrl: './streetlamp-delete-dialog.component.html',
})
export class StreetlampDeleteDialogComponent {
  streetlamp?: IStreetlamp;

  constructor(
    protected streetlampService: StreetlampService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.streetlampService.delete(id).subscribe(() => {
      this.eventManager.broadcast('streetlampListModification');
      this.activeModal.close();
    });
  }
}
