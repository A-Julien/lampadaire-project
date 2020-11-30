import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IRoworder, Roworder } from 'app/shared/model/roworder.model';
import { RoworderService } from './roworder.service';
import { IStreetlamp } from 'app/shared/model/streetlamp.model';
import { StreetlampService } from 'app/entities/streetlamp/streetlamp.service';

@Component({
  selector: 'jhi-roworder-update',
  templateUrl: './roworder-update.component.html',
})
export class RoworderUpdateComponent implements OnInit {
  isSaving = false;
  streetlamps: IStreetlamp[] = [];

  editForm = this.fb.group({
    id: [],
    price: [],
    quantite: [null, [Validators.min(0)]],
    streetlampId: [],
  });

  constructor(
    protected roworderService: RoworderService,
    protected streetlampService: StreetlampService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ roworder }) => {
      this.updateForm(roworder);

      this.streetlampService.query().subscribe((res: HttpResponse<IStreetlamp[]>) => (this.streetlamps = res.body || []));
    });
  }

  updateForm(roworder: IRoworder): void {
    this.editForm.patchValue({
      id: roworder.id,
      price: roworder.price,
      quantite: roworder.quantite,
      streetlampId: roworder.streetlampId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const roworder = this.createFromForm();
    if (roworder.id !== undefined) {
      this.subscribeToSaveResponse(this.roworderService.update(roworder));
    } else {
      this.subscribeToSaveResponse(this.roworderService.create(roworder));
    }
  }

  private createFromForm(): IRoworder {
    return {
      ...new Roworder(),
      id: this.editForm.get(['id'])!.value,
      price: this.editForm.get(['price'])!.value,
      quantite: this.editForm.get(['quantite'])!.value,
      streetlampId: this.editForm.get(['streetlampId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IRoworder>>): void {
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

  trackById(index: number, item: IStreetlamp): any {
    return item.id;
  }
}
