import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IRowcart, Rowcart } from 'app/shared/model/rowcart.model';
import { RowcartService } from './rowcart.service';
import { IStreetlamp } from 'app/shared/model/streetlamp.model';
import { StreetlampService } from 'app/entities/streetlamp/streetlamp.service';
import { ICartpersi } from 'app/shared/model/cartpersi.model';
import { CartpersiService } from 'app/entities/cartpersi/cartpersi.service';

type SelectableEntity = IStreetlamp | ICartpersi;

@Component({
  selector: 'jhi-rowcart-update',
  templateUrl: './rowcart-update.component.html',
})
export class RowcartUpdateComponent implements OnInit {
  isSaving = false;
  streetlamps: IStreetlamp[] = [];
  cartpersis: ICartpersi[] = [];

  editForm = this.fb.group({
    id: [],
    quantity: [],
    streetlampId: [],
    cartpersiId: [],
  });

  constructor(
    protected rowcartService: RowcartService,
    protected streetlampService: StreetlampService,
    protected cartpersiService: CartpersiService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ rowcart }) => {
      this.updateForm(rowcart);

      this.streetlampService.query().subscribe((res: HttpResponse<IStreetlamp[]>) => (this.streetlamps = res.body || []));

      this.cartpersiService.query().subscribe((res: HttpResponse<ICartpersi[]>) => (this.cartpersis = res.body || []));
    });
  }

  updateForm(rowcart: IRowcart): void {
    this.editForm.patchValue({
      id: rowcart.id,
      quantity: rowcart.quantity,
      streetlampId: rowcart.streetlampId,
      cartpersiId: rowcart.cartpersiId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const rowcart = this.createFromForm();
    if (rowcart.id !== undefined) {
      this.subscribeToSaveResponse(this.rowcartService.update(rowcart));
    } else {
      this.subscribeToSaveResponse(this.rowcartService.create(rowcart));
    }
  }

  private createFromForm(): IRowcart {
    return {
      ...new Rowcart(),
      id: this.editForm.get(['id'])!.value,
      quantity: this.editForm.get(['quantity'])!.value,
      streetlampId: this.editForm.get(['streetlampId'])!.value,
      cartpersiId: this.editForm.get(['cartpersiId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IRowcart>>): void {
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

  trackById(index: number, item: SelectableEntity): any {
    return item.id;
  }
}
