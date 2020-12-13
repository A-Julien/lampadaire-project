import { Component, OnInit } from '@angular/core';
import {SOrderService} from 'app/entities/s-order/s-order.service';
import {SOrder} from 'app/shared/model/s-order.model';

import {RoworderService} from 'app/entities/roworder/roworder.service';
import {Roworder} from 'app/shared/model/roworder.model';
import {ApplicationUser} from "../../shared/model/application-user.model";
import {Creditcard} from "../../shared/model/creditcard.model";

import {Account} from "../../core/user/account.model";
import {User} from "../../core/user/user.model";
import {AccountService} from "../../core/auth/account.service";
import {ApplicationUserService} from "../../entities/application-user/application-user.service";
import {UserService} from "../../core/user/user.service";
import {CreditcardService} from "../../entities/creditcard/creditcard.service";

@Component({
  selector: 'jhi-order',
  templateUrl: './order.component.html',
  styleUrls: ['./order.component.scss']
})
export class OrderComponent implements OnInit {
  Account: Account | null = null;
  User: User | null = null;
  applicationUser: ApplicationUser | null = null;
  sorders: SOrder[];
  roworders:Roworder[];
  constructor(private accountService: AccountService, private ap:ApplicationUserService,private us:UserService,
              private ss:SOrderService, private rs:RoworderService) {
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
              this.writreorder();
            });

        });
      });
    }
  }

  writreorder() :void{
    for (let i=0;i<this.sorders.length;i++)
    {
      document.getElementById("plop")!.innerHTML+="<div id='order"+i+"'>";
      document.getElementById("order"+i)!.innerHTML+=this.sorders[i].datecommande!.calendar();
      document.getElementById("plop")!.innerHTML+="<ul id='list"+i+"'>";
      document.getElementById("plop")!.innerHTML+="<p>"+this.sorders[i].roworders!.length+"</p>";
      for (let j=0;j<this.sorders[i].roworders!.length;j++)
      {
        document.getElementById("list"+i)!.innerHTML+="<li id='item"+j+"'>plop</li>";
        document.getElementById("item"+j)!.innerHTML+=this.sorders[i].roworders![j].price +" "+this.sorders[i].roworders![j].quantite;
        document.getElementById("item"+j)!.innerHTML+="plop";
      }
    }

  }

}
