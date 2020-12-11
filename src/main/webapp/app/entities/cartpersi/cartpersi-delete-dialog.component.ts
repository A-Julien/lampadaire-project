import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ICartpersi } from 'app/shared/model/cartpersi.model';
import { CartpersiService } from './cartpersi.service';

@Component({
  templateUrl: './cartpersi-delete-dialog.component.html',
})
export class CartpersiDeleteDialogComponent {
  cartpersi?: ICartpersi;

  constructor(protected cartpersiService: CartpersiService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.cartpersiService.delete(id).subscribe(() => {
      this.eventManager.broadcast('cartpersiListModification');
      this.activeModal.close();
    });
  }
}
