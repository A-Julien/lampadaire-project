import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IRowcart } from 'app/shared/model/rowcart.model';
import { RowcartService } from './rowcart.service';

@Component({
  templateUrl: './rowcart-delete-dialog.component.html',
})
export class RowcartDeleteDialogComponent {
  rowcart?: IRowcart;

  constructor(protected rowcartService: RowcartService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.rowcartService.delete(id).subscribe(() => {
      this.eventManager.broadcast('rowcartListModification');
      this.activeModal.close();
    });
  }
}
