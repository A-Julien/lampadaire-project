import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ICartpersi, Cartpersi } from 'app/shared/model/cartpersi.model';
import { CartpersiService } from './cartpersi.service';

@Component({
  selector: 'jhi-cartpersi-update',
  templateUrl: './cartpersi-update.component.html',
})
export class CartpersiUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
  });

  constructor(protected cartpersiService: CartpersiService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ cartpersi }) => {
      this.updateForm(cartpersi);
    });
  }

  updateForm(cartpersi: ICartpersi): void {
    this.editForm.patchValue({
      id: cartpersi.id,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const cartpersi = this.createFromForm();
    if (cartpersi.id !== undefined) {
      this.subscribeToSaveResponse(this.cartpersiService.update(cartpersi));
    } else {
      this.subscribeToSaveResponse(this.cartpersiService.create(cartpersi));
    }
  }

  private createFromForm(): ICartpersi {
    return {
      ...new Cartpersi(),
      id: this.editForm.get(['id'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICartpersi>>): void {
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
