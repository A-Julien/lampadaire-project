import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IRoworder } from 'app/shared/model/roworder.model';
import { RoworderService } from './roworder.service';

@Component({
  templateUrl: './roworder-delete-dialog.component.html',
})
export class RoworderDeleteDialogComponent {
  roworder?: IRoworder;

  constructor(protected roworderService: RoworderService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.roworderService.delete(id).subscribe(() => {
      this.eventManager.broadcast('roworderListModification');
      this.activeModal.close();
    });
  }
}
