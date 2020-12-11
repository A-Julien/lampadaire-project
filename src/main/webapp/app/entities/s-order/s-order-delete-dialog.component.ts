import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ISOrder } from 'app/shared/model/s-order.model';
import { SOrderService } from './s-order.service';

@Component({
  templateUrl: './s-order-delete-dialog.component.html',
})
export class SOrderDeleteDialogComponent {
  sOrder?: ISOrder;

  constructor(protected sOrderService: SOrderService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.sOrderService.delete(id).subscribe(() => {
      this.eventManager.broadcast('sOrderListModification');
      this.activeModal.close();
    });
  }
}
