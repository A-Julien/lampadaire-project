import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ICreditcard } from 'app/shared/model/creditcard.model';
import { CreditcardService } from './creditcard.service';

@Component({
  templateUrl: './creditcard-delete-dialog.component.html',
})
export class CreditcardDeleteDialogComponent {
  creditcard?: ICreditcard;

  constructor(
    protected creditcardService: CreditcardService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.creditcardService.delete(id).subscribe(() => {
      this.eventManager.broadcast('creditcardListModification');
      this.activeModal.close();
    });
  }
}
