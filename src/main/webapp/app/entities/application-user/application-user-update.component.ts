import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { IApplicationUser, ApplicationUser } from 'app/shared/model/application-user.model';
import { ApplicationUserService } from './application-user.service';
import { IUser } from 'app/core/user/user.model';
import { UserService } from 'app/core/user/user.service';
import { ICartpersi } from 'app/shared/model/cartpersi.model';
import { CartpersiService } from 'app/entities/cartpersi/cartpersi.service';

type SelectableEntity = IUser | ICartpersi;

@Component({
  selector: 'jhi-application-user-update',
  templateUrl: './application-user-update.component.html',
})
export class ApplicationUserUpdateComponent implements OnInit {
  isSaving = false;
  users: IUser[] = [];
  cartpersis: ICartpersi[] = [];

  editForm = this.fb.group({
    id: [],
    siret: [],
    userId: [],
    cartpersiId: [],
  });

  constructor(
    protected applicationUserService: ApplicationUserService,
    protected userService: UserService,
    protected cartpersiService: CartpersiService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ applicationUser }) => {
      this.updateForm(applicationUser);

      this.userService.query().subscribe((res: HttpResponse<IUser[]>) => (this.users = res.body || []));

      this.cartpersiService
        .query({ filter: 'applicationuser-is-null' })
        .pipe(
          map((res: HttpResponse<ICartpersi[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: ICartpersi[]) => {
          if (!applicationUser.cartpersiId) {
            this.cartpersis = resBody;
          } else {
            this.cartpersiService
              .find(applicationUser.cartpersiId)
              .pipe(
                map((subRes: HttpResponse<ICartpersi>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: ICartpersi[]) => (this.cartpersis = concatRes));
          }
        });
    });
  }

  updateForm(applicationUser: IApplicationUser): void {
    this.editForm.patchValue({
      id: applicationUser.id,
      siret: applicationUser.siret,
      userId: applicationUser.userId,
      cartpersiId: applicationUser.cartpersiId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const applicationUser = this.createFromForm();
    if (applicationUser.id !== undefined) {
      this.subscribeToSaveResponse(this.applicationUserService.update(applicationUser));
    } else {
      this.subscribeToSaveResponse(this.applicationUserService.create(applicationUser));
    }
  }

  private createFromForm(): IApplicationUser {
    return {
      ...new ApplicationUser(),
      id: this.editForm.get(['id'])!.value,
      siret: this.editForm.get(['siret'])!.value,
      userId: this.editForm.get(['userId'])!.value,
      cartpersiId: this.editForm.get(['cartpersiId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IApplicationUser>>): void {
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
