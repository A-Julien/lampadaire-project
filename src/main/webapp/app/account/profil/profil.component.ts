import { Component, OnInit } from '@angular/core';

import { ApplicationUserService } from 'app/entities/application-user/application-user.service';
import { ApplicationUser } from 'app/shared/model/application-user.model';

import { SOrderService } from 'app/entities/s-order/s-order.service';
import { SOrder } from 'app/shared/model/s-order.model';

import { RoworderService } from 'app/entities/roworder/roworder.service';
import { Roworder } from 'app/shared/model/roworder.model';

import { CreditcardService } from 'app/entities/creditcard/creditcard.service';
import { Creditcard } from 'app/shared/model/creditcard.model';

import { Account } from 'app/core/user/account.model';
import { User } from 'app/core/user/user.model';

import { AccountService } from 'app/core/auth/account.service';
import { UserService } from 'app/core/user/user.service';

@Component({
  selector: 'jhi-profil',
  templateUrl: './profil.component.html',
  styleUrls: ['./profil.component.scss'],
})
export class ProfilComponent implements OnInit {
  Account: Account | null = null;
  User: User | null = null;
  applicationUser: ApplicationUser | null = null;
  creditcard: Creditcard[];
  sorders: SOrder[];
  roworders: Roworder[];
  constructor(
    private accountService: AccountService,
    private ap: ApplicationUserService,
    private us: UserService,
    private cc: CreditcardService,
    private ss: SOrderService,
    private rs: RoworderService
  ) {
    this.creditcard = [];
    this.sorders = [];
    this.roworders = [];
  }

  ngOnInit(): void {
    if (Account) {
      this.accountService.identity().subscribe(account => {
        this.Account = account;
      });

      this.us.find(this.Account!.login).subscribe(user => {
        this.User = user;

        this.ap.findbyuserid(this.User.id).subscribe((body: any) => {
          this.applicationUser = new ApplicationUser(
            body.body.id,
            body.body.siret,
            body.body.userLogin,
            body.body.userId,
            new SOrder()[0],
            new Creditcard()[0],
            body.body.cartpersiId
          );
        });
      });
    }
  }
}
