import { Component, OnInit } from '@angular/core';

import { ApplicationUserService } from 'app/entities/application-user/application-user.service';
import {ApplicationUser} from 'app/shared/model/application-user.model';
import {SOrder} from 'app/shared/model/s-order.model';
import {Creditcard} from 'app/shared/model/creditcard.model';
import {Account} from "../../core/user/account.model";
import {User} from "../../core/user/user.model";

import {AccountService} from "../../core/auth/account.service";
import {UserService} from "../../core/user/user.service";



@Component({
  selector: 'jhi-profil',
  templateUrl: './profil.component.html',
  styleUrls: ['./profil.component.scss']
})
export class ProfilComponent implements OnInit {
  Account: Account | null = null;
  User: User | null = null;
  applicationUser: ApplicationUser | null = null;
  constructor(private accountService: AccountService, private ap:ApplicationUserService,private us:UserService) {}

  ngOnInit(): void {
    if (Account) {
      this.accountService.identity().subscribe(account => {

        this.Account=account;
      });

      this.us.find(this.Account!.login).subscribe(user =>{
        this.User=user;

        this.ap.findbyuserid(this.User.id).subscribe((body: any)=> {
          this.applicationUser = new ApplicationUser(body.body.id, body.body.siret, body.body.userLogin, body.body.userId, new SOrder()[0], new Creditcard()[0], body.body.cartpersiId)
        });

      });
    }


  }
}
