import { Injectable } from '@angular/core';
import { AccountService } from '../auth/account.service';
import { Cart } from 'app/shared/model/Cart.model';
import { ApplicationUser } from 'app/shared/model/application-user.model';
import { SOrder } from 'app/shared/model/s-order.model';
import { Creditcard } from 'app/shared/model/creditcard.model';
import { ProductOrder } from 'app/shared/model/OrderProduct.model';
import { Streetlamp } from 'app/shared/model/streetlamp.model';
import { ApplicationUserService } from 'app/entities/application-user/application-user.service';
import { UserService } from 'app/core/user/user.service';
import { RowcartService } from 'app/entities/rowcart/rowcart.service';
import { StreetlampService } from 'app/entities/streetlamp/streetlamp.service';
import { Account } from '../../core/user/account.model';
import { LampService } from 'app/core/services/lamp-service.service';
import { Subject } from 'rxjs/internal/Subject';
import { User } from 'app/core/user/user.model';

@Injectable({
  providedIn: 'root',
})
export class UserDataServiceService {
  private _idPer = 'idPersi';
  private _noPer = -1;
  private _cartPersi = 'cartPersi';

  private cartSubject = new Subject();

  //cart!: Cart;
  newUserCart = this.cartSubject.asObservable();

  constructor(
    private accountService: AccountService,
    private ap: ApplicationUserService,
    private us: UserService,
    private rc: RowcartService,
    private ss: UserService,
    private lampService: LampService
  ) {
    localStorage.setItem(this._idPer, JSON.stringify(this._noPer));
    //localStorage.setItem(this._cartPersi, JSON.stringify(undefined));
    /*this.accountService.getAuthenticationState().subscribe(c=>{
      if(c != null) {
        //console.log("HOLLA");
        //console.log(c);
        //this.cartSubject.next(c);
        //await this.updateCart(c);
      }else {
        //localStorage.setItem(this._cartPersi, JSON.stringify(undefined));
        localStorage.setItem(this._idPer, JSON.stringify(this._noPer));
      }
    });*/
  }

  async updateCart(c: any): Promise<any> {
    const cart = new Cart();
    //const res1: User = await this.us.find(this.Account!.login).toPromise();
    const res2: any = await this.ap.findbyuserid(c.id).toPromise();
    const applicationUser = new ApplicationUser(
      res2.body.id,
      res2.body.siret,
      res2.body.userLogin,
      res2.body.userId,
      new SOrder()[0],
      new Creditcard()[0],
      res2.body.cartpersiId
    );
    const res3: any = await this.rc.findByCartpersiId(res2.body.cartpersiId).toPromise();

    for (let i = 0; i < res3.body.length; i++) {
      const plip: any = await this.ss.find(res3.body[i]!.streetlampId!).toPromise();
      cart.addLamp(
        new ProductOrder(
          new Streetlamp(
            plip.body!.id,
            plip.body!.libstreetlamp,
            plip.body!.modelestreetlamp,
            plip.body!.dureeviestreetlamp,
            plip.body!.uniteviestreetlamp,
            plip.body!.materiaustreetlamp,
            plip.body!.liblampe,
            plip.body!.pwlampe,
            plip.body!.formelampe,
            plip.body!.modelelampe,
            plip.body!.dureevielampe,
            plip.body!.unitevielampe,
            plip.body!.voltlampe,
            plip.body!.templampe,
            plip.body!.imagepathstreetlamp,
            plip.body!.stockstreetlamp,
            plip.body!.pricestreetlamp
          ),
          res3.body[i].quantity
        )
      );
    }
    this.lampService.overideCart(cart);
  }

  /*async updateCartPersi (account: Account): Promise<any> {
    const cart = new Cart();

    const res1: User = await this.us.find(account.login).toPromise();
    const res2: any = await this.ap.findbyuserid(res1.id).toPromise();
    const applicationUser = new ApplicationUser(
      res2.body.id,
      res2.body.siret,
      res2.body.userLogin,
      res2.body.userId,
      new SOrder()[0],
      new Creditcard()[0],
      res2.body.cartpersiId
    );
    const res3: any = await this.rc.findByCartpersiId(res2.body.cartpersiId).toPromise();

    for (let i = 0; i < res3.body.length; i++) {
      const plip: any = await this.ss.find(res3.body[i]!.streetlampId!).toPromise();
      cart.addLamp(
        new ProductOrder(
          new Streetlamp(
            plip.body!.id,
            plip.body!.libstreetlamp,
            plip.body!.modelestreetlamp,
            plip.body!.dureeviestreetlamp,
            plip.body!.uniteviestreetlamp,
            plip.body!.materiaustreetlamp,
            plip.body!.liblampe,
            plip.body!.pwlampe,
            plip.body!.formelampe,
            plip.body!.modelelampe,
            plip.body!.dureevielampe,
            plip.body!.unitevielampe,
            plip.body!.voltlampe,
            plip.body!.templampe,
            plip.body!.imagepathstreetlamp,
            plip.body!.stockstreetlamp,
            plip.body!.pricestreetlamp
          ),
          res3.body[i].quantity
        )
      );
    }
    //this.lampService.overideCart(cart);
    this.cartSubject.next(cart);
  }*/
}
