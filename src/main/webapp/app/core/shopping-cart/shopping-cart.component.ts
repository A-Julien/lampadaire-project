import { Component, EventEmitter, OnDestroy, OnInit, Output } from '@angular/core';
import { ProductOrder } from 'app/shared/model/OrderProduct.model';
import { Cart } from 'app/shared/model/Cart.model';
import { Subscription } from 'rxjs';
import { LampService } from 'app/core/services/lamp-service.service';

import {Account} from "../../core/user/account.model";
import {User} from "../../core/user/user.model";

import {AccountService} from "../../core/auth/account.service";
import {UserService} from "../../core/user/user.service";
import { ApplicationUserService } from 'app/entities/application-user/application-user.service';
import {ApplicationUser} from 'app/shared/model/application-user.model';

import { RowcartService } from 'app/entities/rowcart/rowcart.service';
import {Rowcart} from 'app/shared/model/rowcart.model';

import {SOrder} from 'app/shared/model/s-order.model';
import {Creditcard} from 'app/shared/model/creditcard.model';
import {Streetlamp} from "app/shared/model/streetlamp.model";
import {StreetlampService} from 'app/entities/streetlamp/streetlamp.service';

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
  Account: Account | null = null;
  User: User | null = null;
  applicationUser: ApplicationUser | null = null;

  @Output() onOrderFinished: EventEmitter<boolean>;

  constructor(private lampService: LampService,private accountService: AccountService, private ap:ApplicationUserService,private us:UserService,
              private rc :RowcartService, private ss:StreetlampService) {
    this.total = 0;
    this.orderFinished = false;
    this.onOrderFinished = new EventEmitter<boolean>();
  }

  ngOnInit(): void {
    this.orders = new Cart();

    if (Account && this.lampService.ProductOrders.productOrders.length===0) {
      this.accountService.identity().subscribe(account => {

        this.Account=account;
      });

      this.us.find(this.Account!.login).subscribe(user =>{
        this.User=user;

        this.ap.findbyuserid(this.User.id).subscribe((body: any)=> {
          this.applicationUser = new ApplicationUser(body.body.id, body.body.siret, body.body.userLogin, body.body.userId, new SOrder()[0], new Creditcard()[0], body.body.cartpersiId);
          this.rc.findByCartpersiId(body.body.cartpersiId).subscribe((plop:any) => {
            for (let i=0;i<plop.body.length;i++)
            {
              this.ss.find(plop.body[i]!.streetlampId!).subscribe((plip:any)=>{

                this.orders.productOrders.push(new ProductOrder(new Streetlamp(plip.body!.id,plip.body!.libstreetlamp,plip.body!.modelestreetlamp,plip.body!.dureeviestreetlamp,plip.body!.uniteviestreetlamp,
                  plip.body!.materiaustreetlamp,plip.body!.liblampe,plip.body!.pwlampe,plip.body!.formelampe,plip.body!.modelelampe,plip.body!.dureevielampe,plip.body!.unitevielampe,
                  plip.body!.voltlampe,plip.body!.templampe,plip.body!.imagepathstreetlamp,plip.body!.stockstreetlamp,plip.body!.pricestreetlamp),plop.body[i].quantity));
              });
            }
            this.lampService.ProductOrders=this.orders;

          });
        });

      });
    }
    this.loadCart();
    this.loadTotal();

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
    this.orders = this.lampService.ProductOrders;
    this.sub = this.lampService.ProductOrderChanged.subscribe(() => {
      const productOrder = this.lampService.SelectedProductOrder;
      if (productOrder) {
        this.orders.productOrders.push(new ProductOrder(productOrder.product, productOrder.quantity));
      }
      this.lampService.ProductOrders = this.orders;
      // this.orders = this.lampService.ProductOrders;
      this.total = this.calculateTotal(this.orders.productOrders);
    });
  }

  finishOrder(): void {
    this.orderFinished = true;
    this.lampService.Total = this.total;
    this.onOrderFinished.emit(this.orderFinished);
  }

  reset(): void {
    this.orderFinished = false;
    this.orders = new Cart();
    this.orders.productOrders = [];
    this.loadTotal();
    this.total = 0;
  }

  ngOnDestroy(): void {
    this.sub.unsubscribe();
    delete this.orders;
  }
}
