import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IStreetlamp, Streetlamp } from 'app/shared/model/streetlamp.model';
import { StreetlampService } from './streetlamp.service';

@Component({
  selector: 'jhi-streetlamp-update',
  templateUrl: './streetlamp-update.component.html',
})
export class StreetlampUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    libstreetlamp: [],
    modelestreetlamp: [],
    dureeviestreetlamp: [],
    uniteviestreetlamp: [],
    materiaustreetlamp: [],
    liblampe: [],
    pwlampe: [],
    formelampe: [],
    modelelampe: [],
    dureevielampe: [],
    unitevielampe: [],
    voltlampe: [],
    templampe: [],
  });

  constructor(protected streetlampService: StreetlampService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ streetlamp }) => {
      this.updateForm(streetlamp);
    });
  }

  updateForm(streetlamp: IStreetlamp): void {
    this.editForm.patchValue({
      id: streetlamp.id,
      libstreetlamp: streetlamp.libstreetlamp,
      modelestreetlamp: streetlamp.modelestreetlamp,
      dureeviestreetlamp: streetlamp.dureeviestreetlamp,
      uniteviestreetlamp: streetlamp.uniteviestreetlamp,
      materiaustreetlamp: streetlamp.materiaustreetlamp,
      liblampe: streetlamp.liblampe,
      pwlampe: streetlamp.pwlampe,
      formelampe: streetlamp.formelampe,
      modelelampe: streetlamp.modelelampe,
      dureevielampe: streetlamp.dureevielampe,
      unitevielampe: streetlamp.unitevielampe,
      voltlampe: streetlamp.voltlampe,
      templampe: streetlamp.templampe,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const streetlamp = this.createFromForm();
    if (streetlamp.id !== undefined) {
      this.subscribeToSaveResponse(this.streetlampService.update(streetlamp));
    } else {
      this.subscribeToSaveResponse(this.streetlampService.create(streetlamp));
    }
  }

  private createFromForm(): IStreetlamp {
    return {
      ...new Streetlamp(),
      id: this.editForm.get(['id'])!.value,
      libstreetlamp: this.editForm.get(['libstreetlamp'])!.value,
      modelestreetlamp: this.editForm.get(['modelestreetlamp'])!.value,
      dureeviestreetlamp: this.editForm.get(['dureeviestreetlamp'])!.value,
      uniteviestreetlamp: this.editForm.get(['uniteviestreetlamp'])!.value,
      materiaustreetlamp: this.editForm.get(['materiaustreetlamp'])!.value,
      liblampe: this.editForm.get(['liblampe'])!.value,
      pwlampe: this.editForm.get(['pwlampe'])!.value,
      formelampe: this.editForm.get(['formelampe'])!.value,
      modelelampe: this.editForm.get(['modelelampe'])!.value,
      dureevielampe: this.editForm.get(['dureevielampe'])!.value,
      unitevielampe: this.editForm.get(['unitevielampe'])!.value,
      voltlampe: this.editForm.get(['voltlampe'])!.value,
      templampe: this.editForm.get(['templampe'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IStreetlamp>>): void {
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
