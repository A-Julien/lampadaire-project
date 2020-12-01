import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ISOrder, SOrder } from 'app/shared/model/s-order.model';
import { SOrderService } from './s-order.service';

@Component({
  selector: 'jhi-s-order-update',
  templateUrl: './s-order-update.component.html',
})
export class SOrderUpdateComponent implements OnInit {
  isSaving = false;
  datecommandeDp: any;

  editForm = this.fb.group({
    id: [],
    datecommande: [],
  });

  constructor(protected sOrderService: SOrderService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ sOrder }) => {
      this.updateForm(sOrder);
    });
  }

  updateForm(sOrder: ISOrder): void {
    this.editForm.patchValue({
      id: sOrder.id,
      datecommande: sOrder.datecommande,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const sOrder = this.createFromForm();
    if (sOrder.id !== undefined) {
      this.subscribeToSaveResponse(this.sOrderService.update(sOrder));
    } else {
      this.subscribeToSaveResponse(this.sOrderService.create(sOrder));
    }
  }

  private createFromForm(): ISOrder {
    return {
      ...new SOrder(),
      id: this.editForm.get(['id'])!.value,
      datecommande: this.editForm.get(['datecommande'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ISOrder>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }
}
