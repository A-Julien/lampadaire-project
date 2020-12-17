import { Component, EventEmitter, OnDestroy, OnInit, Output } from '@angular/core';
import { ProductOrder } from 'app/shared/model/OrderProduct.model';
import { Cart } from 'app/shared/model/Cart.model';
import { Subscription } from 'rxjs';
import { LampService } from 'app/core/services/lamp-service.service';

import { Account } from '../../core/user/account.model';
import { IUser, User } from '../../core/user/user.model';

import { AccountService } from '../../core/auth/account.service';
import { UserService } from '../../core/user/user.service';
import { ApplicationUserService } from 'app/entities/application-user/application-user.service';
import { ApplicationUser } from 'app/shared/model/application-user.model';

import { RowcartService } from 'app/entities/rowcart/rowcart.service';
import { Rowcart } from 'app/shared/model/rowcart.model';

import { SOrder } from 'app/shared/model/s-order.model';
import { Creditcard } from 'app/shared/model/creditcard.model';
import { Streetlamp } from 'app/shared/model/streetlamp.model';
import { StreetlampService } from 'app/entities/streetlamp/streetlamp.service';
import { flatMap } from 'rxjs/operators';
import { UserDataServiceService } from 'app/core/services/user-data-service.service';

@Component({
  selector: 'jhi-shopping-cart',
  templateUrl: './shopping-cart.component.html',
  styleUrls: ['./shopping-cart.component.scss'],
})
export class ShoppingCartComponent implements OnInit, OnDestroy {
  orderFinished: boolean;
  orders!: Cart;
  total: number;
  sub!: Subscription;
  account: Account | null = null;
  User: User | null = null;
  applicationUser: ApplicationUser | null = null;

  @Output() onOrderFinished: EventEmitter<boolean>;

  constructor(
    private lampService: LampService,
    private accountService: AccountService,
    private ap: ApplicationUserService,
    private us: UserService,
    private rc: RowcartService,
    private ss: StreetlampService
  ) {
    this.total = 0;
    this.orderFinished = false;
    this.onOrderFinished = new EventEmitter<boolean>();
  }

  ngOnInit(): void {
    //this.orders = new Cart();
    this.orders = this.lampService.get();

    this.loadCart();

    //this.loadTotal();
    this.total = this.calculateTotal(this.orders.productOrders);

    /*this.uss.newUserCart.subscribe(c=>{
      if(c != null) this.uss.updateCart(c);
    });*/
    this.uCart();
    /*if (Account && this.lampService.ProductOrders.productOrders.length === 0) {
      this.accountService.identity().subscribe(account => {
        this.account = account;
      });*/
    //this.uCart();
    //}
  }

  async uCart(): Promise<any> {
    console.log('UCART');

    const a: any = await this.accountService.identity().toPromise();

    /*const id = localStorage.getItem('idPersi');
    let idp: number | null = null;
    if(id != null) idp = (JSON.parse(id) as number);*/

    if (a) {
      console.log('MAJ');
      const c: User = await this.us.find(a.login).toPromise();
      console.log('c' + c);
      //localStorage.setItem('idPersi', JSON.stringify(-1));
      //if(idp != null && idp > 0 ) {
      console.log('updateC');
      this.updateCartFromBD(c);
      //localStorage.setItem('idCo', JSON.stringify(c.id));
      //}
    } else {
      localStorage.setItem('idPersi', JSON.stringify(-1));
    }

    console.log('a' + a);

    /*if(a !== null){
      console.log("MAJ");
      console.log("idd" + idd);
      const  c: User = await this.us.find(a.login).toPromise();
      console.log("c" + c);
      //localStorage.setItem('idPersi', JSON.stringify(-1));
      if(idd === null || idd === -1 ) {
        console.log("updateC");
        this.myFunction(c);
        localStorage.setItem('idCo', JSON.stringify(c.id));
      }
    }*/
  }

  async updateCartFromBD(c: any): Promise<any> {
    //const res1: User = await this.us.find(this.Account!.login).toPromise();
    const res2: any = await this.ap.findbyuserid(c.id).toPromise();
    this.applicationUser = new ApplicationUser(
      res2.body.id,
      res2.body.siret,
      res2.body.userLogin,
      res2.body.userId,
      new SOrder()[0],
      new Creditcard()[0],
      res2.body.cartpersiId
    );
    const res3: any = await this.rc.findByCartpersiId(res2.body.cartpersiId).toPromise();
    localStorage.setItem('idPersi', JSON.stringify(res2.body.cartpersiId));
    for (let i = 0; i < res3.body.length; i++) {
      const plip: any = await this.ss.find(res3.body[i]!.streetlampId!).toPromise();
      this.orders.addLamp(
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
    this.lampService.overideCart(this.orders);
  }

  loadTotal(): void {
    this.sub = this.lampService.OrdersChanged.subscribe(() => {
      this.total = this.calculateTotal(this.orders.productOrders);
    });
  }

  private calculateTotal(products: ProductOrder[]): number {
    let sum = 0;
    products.forEach(value => {
      if (value.product.pricestreetlamp !== undefined) {
        sum += value.product.pricestreetlamp * value.quantity;
      }
    });
    return sum;
  }

  loadCart(): void {
    this.sub = this.lampService.ProductOrderChanged.subscribe(() => {
      console.log('NEW ITEM IN CART');
      this.orders = this.lampService.get();
      //console.log(this.orders.lamps);
      this.total = this.calculateTotal(this.orders.lamps);
      /*const productOrder = this.lampService.SelectedProductOrder;
      if (productOrder) {
        this.orders.productOrders.push(new ProductOrder(productOrder.product, productOrder.quantity));
      }
      this.lampService.ProductOrders = this.orders;
      // this.orders = this.lampService.ProductOrders;
      this.total = this.calculateTotal(this.orders.productOrders);*/
    });
    /*this.orders = this.lampService.ProductOrders;
    this.sub = this.lampService.ProductOrderChanged.subscribe(() => {
      const productOrder = this.lampService.SelectedProductOrder;
      if (productOrder) {
        this.orders.productOrders.push(new ProductOrder(productOrder.product, productOrder.quantity));
      }
      this.lampService.ProductOrders = this.orders;
      // this.orders = this.lampService.ProductOrders;
      this.total = this.calculateTotal(this.orders.productOrders);
    });*/
  }

  finishOrder(): void {
    //this.orderFinished = true;
    this.lampService.Total = this.total;
    this.onOrderFinished.emit(this.orderFinished);
  }

  reset(): void {
    this.orderFinished = false;
    this.orders = new Cart();
    this.orders.productOrders = [];
    this.total = 0;
  }

  ngOnDestroy(): void {
    //this.sub.unsubscribe();
    delete this.orders;
  }
}
