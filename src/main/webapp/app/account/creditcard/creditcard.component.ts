import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { ApplicationUser } from '../../shared/model/application-user.model';

import { CreditcardService } from 'app/entities/creditcard/creditcard.service';
import { Creditcard } from '../../shared/model/creditcard.model';

import { Account } from '../../core/user/account.model';
import { User } from '../../core/user/user.model';
import { AccountService } from '../../core/auth/account.service';
import { ApplicationUserService } from '../../entities/application-user/application-user.service';
import { UserService } from '../../core/user/user.service';
import { SOrder } from '../../shared/model/s-order.model';
import * as moment from 'moment';

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
  selectedCreditCard: Creditcard;

  @Output() selectedCardEvent = new EventEmitter<Creditcard>();
  @Input() profileView = true;

  constructor(
    private accountService: AccountService,
    private ap: ApplicationUserService,
    private us: UserService,
    private cs: CreditcardService
  ) {
    this.creditcards = [];
    this.selectedCreditCard = new Creditcard();
  }

  ngOnInit(): void {
    /*this.creditcards.push(new Creditcard(0, '123', moment(), '2', 0));
    this.creditcards.push(new Creditcard(1, '123', moment(), '22', 0));
    this.creditcards.push(new Creditcard(2, '123', moment(), '22', 0));
    this.creditcards.push(new Creditcard(3, '123', moment(), '23', 0));*/

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

  setCardSelected(creditcard: Creditcard): void {
    this.selectedCardEvent.emit(creditcard);
  }

  previousState(): void {
    window.history.back();
  }
}
