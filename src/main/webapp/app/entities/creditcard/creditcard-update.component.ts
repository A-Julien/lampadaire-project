import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ICreditcard, Creditcard } from 'app/shared/model/creditcard.model';
import { CreditcardService } from './creditcard.service';
import { IApplicationUser } from 'app/shared/model/application-user.model';
import { ApplicationUserService } from 'app/entities/application-user/application-user.service';

@Component({
  selector: 'jhi-creditcard-update',
  templateUrl: './creditcard-update.component.html',
})
export class CreditcardUpdateComponent implements OnInit {
  isSaving = false;
  applicationusers: IApplicationUser[] = [];
  dateexpirationDp: any;

  editForm = this.fb.group({
    id: [],
    numcarte: [],
    dateexpiration: [],
    code: [],
    applicationUserId: [],
  });

  constructor(
    protected creditcardService: CreditcardService,
    protected applicationUserService: ApplicationUserService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ creditcard }) => {
      this.updateForm(creditcard);

      this.applicationUserService.query().subscribe((res: HttpResponse<IApplicationUser[]>) => (this.applicationusers = res.body || []));
    });
  }

  updateForm(creditcard: ICreditcard): void {
    this.editForm.patchValue({
      id: creditcard.id,
      numcarte: creditcard.numcarte,
      dateexpiration: creditcard.dateexpiration,
      code: creditcard.code,
      applicationUserId: creditcard.applicationUserId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const creditcard = this.createFromForm();
    if (creditcard.id !== undefined) {
      this.subscribeToSaveResponse(this.creditcardService.update(creditcard));
    } else {
      this.subscribeToSaveResponse(this.creditcardService.create(creditcard));
    }
  }

  private createFromForm(): ICreditcard {
    return {
      ...new Creditcard(),
      id: this.editForm.get(['id'])!.value,
      numcarte: this.editForm.get(['numcarte'])!.value,
      dateexpiration: this.editForm.get(['dateexpiration'])!.value,
      code: this.editForm.get(['code'])!.value,
      applicationUserId: this.editForm.get(['applicationUserId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICreditcard>>): void {
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

  trackById(index: number, item: IApplicationUser): any {
    return item.id;
  }
}
