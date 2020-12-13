import { Component, OnInit } from '@angular/core';

import { ApplicationUserService } from 'app/entities/application-user/application-user.service';
import {ApplicationUser} from 'app/shared/model/application-user.model';

import {SOrderService} from 'app/entities/s-order/s-order.service';
import {SOrder} from 'app/shared/model/s-order.model';

import {RoworderService} from 'app/entities/roworder/roworder.service';
import {Roworder} from 'app/shared/model/roworder.model';

import {CreditcardService} from 'app/entities/creditcard/creditcard.service';
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
  creditcard: Creditcard[];
  sorders: SOrder[];
  roworders:Roworder[];
  constructor(private accountService: AccountService, private ap:ApplicationUserService,private us:UserService, private cc :CreditcardService,
              private ss:SOrderService, private rs:RoworderService) {

    this.creditcard=[];
    this.sorders=[];
    this.roworders=[];
  }

  ngOnInit(): void {
    if (Account) {
      this.accountService.identity().subscribe(account => {

        this.Account=account;
      });

      this.us.find(this.Account!.login).subscribe(user =>{
        this.User=user;

        this.ap.findbyuserid(this.User.id).subscribe((body: any)=> {
          this.applicationUser = new ApplicationUser(body.body.id, body.body.siret, body.body.userLogin, body.body.userId, new SOrder()[0], new Creditcard()[0], body.body.cartpersiId);
          this.cc.findByApplicationUserId(body.body.id).subscribe((plop: any)=> {
            for (let i=0;i<plop.body.length;i++){
              this.creditcard.push(new Creditcard(plop.body[i].id,plop.body[i].numcarte,plop.body[i].dateexpiration,plop.body[i].applicationUserId));
            }
            this.ss.findByApplicationUserId(body.body.id).subscribe((plup: any)=> {
              for (let i=0;i<plup.body.length;i++){

                this.roworders=[]
                this.rs.findBySOrderId(plup.body[i].id).subscribe((plip:any) => {

                  for (let j=0;j<plip.body.length;j++){
                    this.roworders.push(new Roworder(plip.body[j].id,plip.body[j].price,plip.body[j].quantite,plip.body[j].streetlampId,plip.body[j].sorderId));
                  }

                });

                this.sorders.push(new SOrder(plup.body[i].id,plup.body[i].datecommande,this.roworders,plup.body[i].applicationUserId));

              }
            });
          });
          //alert(this.roworders[1].streetlampId);

        });

      });
    }


  }
}
