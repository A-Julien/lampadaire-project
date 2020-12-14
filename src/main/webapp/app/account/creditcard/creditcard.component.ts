import { Component, OnInit } from '@angular/core';
import { ApplicationUser } from '../../shared/model/application-user.model';

import { CreditcardService } from 'app/entities/creditcard/creditcard.service';
import { Creditcard } from '../../shared/model/creditcard.model';

import { Account } from '../../core/user/account.model';
import { User } from '../../core/user/user.model';
import { AccountService } from '../../core/auth/account.service';
import { ApplicationUserService } from '../../entities/application-user/application-user.service';
import { UserService } from '../../core/user/user.service';
import { SOrder } from '../../shared/model/s-order.model';
import { Roworder } from '../../shared/model/roworder.model';

@Component({
  selector: 'jhi-creditcard',
  templateUrl: './creditcard.component.html',
  styleUrls: ['./creditcard.component.scss'],
})
export class CreditcardComponent implements OnInit {
  Account: Account | null = null;
  User: User | null = null;
  applicationUser: ApplicationUser | null = null;
  creditcards: Creditcard[];
  constructor(
    private accountService: AccountService,
    private ap: ApplicationUserService,
    private us: UserService,
    private cs: CreditcardService
  ) {
    this.creditcards = [];
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
          this.cs.findByApplicationUserId(body.body.id).subscribe((plop: any) => {
            for (let i = 0; i < plop.body.length; i++) {
              this.creditcards.push(
                new Creditcard(plop.body[i].id, plop.body[i].numcarte, plop.body[i].dateexpiration, plop.body[i].code, body.body.id)
              );
            }
          });
        });
      });
    }
  }
}
