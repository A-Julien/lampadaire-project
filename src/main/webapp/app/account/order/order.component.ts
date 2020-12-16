import { Component, OnInit } from '@angular/core';
import { SOrderService } from 'app/entities/s-order/s-order.service';
import { SOrder } from 'app/shared/model/s-order.model';

import { RoworderService } from 'app/entities/roworder/roworder.service';
import { Roworder } from 'app/shared/model/roworder.model';

import { StreetlampService } from 'app/entities/streetlamp/streetlamp.service';
import { Streetlamp } from 'app/shared/model/streetlamp.model';

import { ApplicationUser } from '../../shared/model/application-user.model';
import { Creditcard } from '../../shared/model/creditcard.model';

import { Account } from '../../core/user/account.model';
import { User } from '../../core/user/user.model';
import { AccountService } from '../../core/auth/account.service';
import { ApplicationUserService } from '../../entities/application-user/application-user.service';
import { UserService } from '../../core/user/user.service';
import { PdfServiceService } from 'app/core/services/pdf-service.service';
import { Cart } from 'app/shared/model/Cart.model';
import { ProductOrder } from 'app/shared/model/OrderProduct.model';
import { Observable, of } from 'rxjs';
import { logger } from 'codelyzer/util/logger';
import { log } from 'util';

/*declare global{
  let l: any;//new Map<number, any>();
}*/

@Component({
  selector: 'jhi-order',
  templateUrl: './order.component.html',
  styleUrls: ['./order.component.scss'],
})
export class OrderComponent implements OnInit {
  Account: Account | null = null;
  User: User | null = null;
  applicationUser: ApplicationUser | null = null;
  sorders: SOrder[];
  roworders: Roworder[];
  lamps: Map<number, any>;
  l: Map<number, any>;
  prodList: Map<number, SOrder>;

  constructor(
    private accountService: AccountService,
    private ap: ApplicationUserService,
    private us: UserService,
    private ss: SOrderService,
    private rs: RoworderService,
    private st: StreetlampService,
    private pdfService: PdfServiceService
  ) {
    this.sorders = [];
    this.roworders = [];
    this.lamps = new Map<number, Streetlamp>();
    this.l = new Map<number, Streetlamp>();
    this.prodList = new Map<number, SOrder>();
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
          this.ss.findByApplicationUserId(body.body.id).subscribe((plup: any) => {
            for (let i = 0; i < plup.body.length; i++) {
              this.rs.findBySOrderId(plup.body[i].id).subscribe((plip: any) => {
                this.roworders = [];
                for (let j = 0; j < plip.body.length; j++) {
                  this.roworders.push(
                    new Roworder(
                      plip.body[j].id,
                      plip.body[j].price,
                      plip.body[j].quantite,
                      plip.body[j].streetlampId,
                      plip.body[j].sorderId
                    )
                  );
                  this.st.find(plip.body[j].streetlampId).subscribe(plap => {
                    this.lamps.set(
                      plip.body[j].id,
                      new Streetlamp(
                        plap.body!.id,
                        plap.body!.libstreetlamp,
                        plap.body!.modelestreetlamp,
                        plap.body!.dureeviestreetlamp,
                        plap.body!.uniteviestreetlamp,
                        plap.body!.materiaustreetlamp,
                        plap.body!.liblampe,
                        plap.body!.pwlampe,
                        plap.body!.formelampe,
                        plap.body!.modelelampe,
                        plap.body!.dureevielampe,
                        plap.body!.unitevielampe,
                        plap.body!.voltlampe,
                        plap.body!.templampe,
                        plap.body!.imagepathstreetlamp,
                        plap.body!.stockstreetlamp,
                        plap.body!.pricestreetlamp
                      )
                    );
                    /*l.set(plip.body[j].id,
                      new Streetlamp(
                        plap.body!.id,
                        plap.body!.libstreetlamp,
                        plap.body!.modelestreetlamp,
                        plap.body!.dureeviestreetlamp,
                        plap.body!.uniteviestreetlamp,
                        plap.body!.materiaustreetlamp,
                        plap.body!.liblampe,
                        plap.body!.pwlampe,
                        plap.body!.formelampe,
                        plap.body!.modelelampe,
                        plap.body!.dureevielampe,
                        plap.body!.unitevielampe,
                        plap.body!.voltlampe,
                        plap.body!.templampe,
                        plap.body!.imagepathstreetlamp,
                        plap.body!.stockstreetlamp,
                        plap.body!.pricestreetlamp
                      ));*/
                    this.setL(this.lamps);
                  });
                }
                //this.setForm
                //this.prodList.set(plup.body[i].id, new SOrder(plup.body[i].id, plup.body[i].datecommande, this.roworders, plup.body[i].applicationUserId));
                this.sorders.push(new SOrder(plup.body[i].id, plup.body[i].datecommande, this.roworders, plup.body[i].applicationUserId));
              });
            }
          });
        });
      });
    }
  }

  setL(ll: Map<number, Streetlamp>): void {
    this.l = ll;
  }

  createBill(order: SOrder): void {
    //this.lamps.get(order.roworders[0].rostreetlampId);
    /*const bite = new Observable((observer) => {
      const cart = new Cart();

      order.roworders?.forEach(o =>{
        if(o.streetlampId!== undefined ){
          this.st.find(o.streetlampId).subscribe(plap => {
            if(o.quantite !== undefined) {
              cart.productOrders.push(new ProductOrder(new Streetlamp(
                plap.body!.id,
                plap.body!.libstreetlamp,
                plap.body!.modelestreetlamp,
                plap.body!.dureeviestreetlamp,
                plap.body!.uniteviestreetlamp,
                plap.body!.materiaustreetlamp,
                plap.body!.liblampe,
                plap.body!.pwlampe,
                plap.body!.formelampe,
                plap.body!.modelelampe,
                plap.body!.dureevielampe,
                plap.body!.unitevielampe,
                plap.body!.voltlampe,
                plap.body!.templampe,
                plap.body!.imagepathstreetlamp,
                plap.body!.stockstreetlamp,
                plap.body!.pricestreetlamp
              ), o.quantite));
            }
          });
        }
      });*/

    const cart = new Cart();
    console.log('---------------');
    order.roworders?.forEach(o => {
      console.log(o);
    });
    console.log('---------------');
    this.lamps.forEach(o => {
      console.log(o);
    });
    console.log('---------------');
    order.roworders?.forEach(o => {
      if (o.streetlampId !== undefined && o.quantite !== undefined && o.id !== undefined) {
        console.log(this.lamps.get(o.id));
        cart.productOrders.push(new ProductOrder(this.lamps.get(o.id), o.quantite));
      }
    });
    this.pdfService.addProduct(cart.productOrders);
    this.pdfService.generatePDF();

    //bite.subscribe();
    //order.datecommande;
  }
}
