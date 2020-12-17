import { Injectable } from '@angular/core';
import { ProductOrder } from '../../shared/model/OrderProduct.model';
import { Cart } from '../../shared/model/Cart.model';
import { Subject } from 'rxjs/internal/Subject';
import { HttpClient } from '@angular/common/http';
import { Observable, Subscription } from 'rxjs';
import { RowcartService } from 'app/entities/rowcart/rowcart.service';
import { Rowcart } from 'app/shared/model/rowcart.model';
import { IStreetlamp, Streetlamp } from 'app/shared/model/streetlamp.model';
import { UserDataServiceService } from 'app/core/services/user-data-service.service';
import { AccountService } from 'app/core/auth/account.service';
import { Account } from 'app/core/user/account.model';
import { ApplicationUser } from 'app/shared/model/application-user.model';
import { SOrder } from 'app/shared/model/s-order.model';
import { Creditcard } from 'app/shared/model/creditcard.model';
import { ApplicationUserService } from 'app/entities/application-user/application-user.service';
import { UserService } from 'app/core/user/user.service';
import { StreetlampService } from 'app/entities/streetlamp/streetlamp.service';
import { User } from 'app/core/user/user.model';

@Injectable({
  providedIn: 'root',
})
export class LampService {
  private productsUrl = '/api/streetlamps';
  private ordersUrl = '/api/orders';
  private _idPer = 'idPersi';
  private _noPer = -1;
  private _cartPersi = 'cartPersi';

  private productOrder!: ProductOrder;
  private orders: Cart = new Cart();

  private productOrderSubject = new Subject();
  private ordersSubject = new Subject();
  private totalSubject = new Subject();

  private total: number;
  private idcartpresi: number;

  sub!: Subscription;

  ProductOrderChanged = this.productOrderSubject.asObservable();
  OrdersChanged = this.ordersSubject.asObservable();
  TotalChanged = this.totalSubject.asObservable();

  constructor(private accountService: AccountService, private http: HttpClient, private rc: RowcartService) {
    this.total = 0;
    this.idcartpresi = -1;
    //localStorage.setItem('idPersi', JSON.stringify(-1));
    localStorage.setItem('LampCart', JSON.stringify(new Cart()));

    //this.accountService.getAuthenticationState().subscribe(account=>{if(account != null) this.overideCart();});
    localStorage.setItem(this._idPer, JSON.stringify(this._noPer));
    //localStorage.setItem(this._cartPersi, JSON.stringify(undefined));
    /*this.accountService.getAuthenticationState().subscribe(account=>{
      if(account != null) {
        console.log("HOLLA");
        console.log(account);
        this.updateCartPersi(account);
      }else {
       // localStorage.setItem(this._cartPersi, JSON.stringify(undefined));
        localStorage.setItem(this._idPer, JSON.stringify(this._noPer));
      }
    });*/
    /* this.sub = this.uss.newUserCart.subscribe((c)=>{
      console.log("sub");
      console.log(c);
      //console.log(this.uss.cart);
      this.overideCart(c as Cart);
    });*/
  }

  set SelectedProductOrder(value: ProductOrder) {
    this.productOrder = value;
    this.productOrderSubject.next();
    //cart persi
    if (this.idcartpresi !== -1) {
      //this.rc.create(new Rowcart(undefined, value.quantity, value.product.id, this.idcartpresi)).subscribe();
    }
    localStorage.setItem('cart', JSON.stringify(this.ProductOrders.productOrders));
  }

  saveOrder(order: Cart): Observable<any> {
    return this.http.post(this.ordersUrl, order);
  }

  set Idcartpresi(id: any) {
    this.idcartpresi = id;
  }

  set saveCart(cart: ProductOrder[]) {
    localStorage.setItem('cart', JSON.stringify(cart));
  }

  get(): Cart {
    const cartStorage = localStorage.getItem('LampCart');

    const cart = new Cart();

    if (cartStorage != null) {
      cart.cp(JSON.parse(cartStorage) as Cart);
    }

    return cart;
  }

  public overideCart(cart: Cart): void {
    console.log('OVERIDE CART');

    console.log(cart);

    if (cart != null) {
      localStorage.setItem('LampCart', JSON.stringify(cart));
    }

    this.productOrderSubject.next();
  }

  public addLamp(lamp: ProductOrder): void {
    const cart = this.get();

    cart.addLamp(lamp);

    localStorage.setItem('LampCart', JSON.stringify(cart));

    this.productOrderSubject.next();

    //console.log(this.getIdPersi());
    if (this.getIdPersi() !== -1) {
      console.log('wsh create');
      this.rc.create(new Rowcart(undefined, lamp.quantity, lamp.product.id, this.getIdPersi())).subscribe();
    }
  }

  public removeLamp(lamp: ProductOrder): void {
    const cart = this.get();

    cart.removeLamp(lamp);

    localStorage.setItem('LampCart', JSON.stringify(cart));

    this.productOrderSubject.next();

    this.rmPersi(lamp);
  }

  getIdPersi(): number {
    const idp = localStorage.getItem(this._idPer);
    if (idp != null) return JSON.parse(idp) as number;
    return -1;
  }

  public calculateTotal(products: ProductOrder[]): number {
    let sum = 0;
    products.forEach(value => {
      if (value.product.pricestreetlamp !== undefined) {
        sum += value.product.pricestreetlamp * value.quantity;
      }
    });
    return sum;
  }

  get ProductOrders(): Cart {
    return this.orders;
  }

  get Total(): number {
    return this.total;
  }

  set Total(value: number) {
    this.total = value;
    this.totalSubject.next();
  }

  //remove commandlogne in bd
  async rmPersi(value: ProductOrder): Promise<void> {
    if (this.getIdPersi() !== -1) {
      const plop: any = await this.rc.findByCartpersiId(this.getIdPersi()).toPromise();
      for (let i = 0; i < plop.body.length; i++) {
        if (value.product.id === plop.body[i]!.streetlampId!) {
          this.rc.delete(Number(plop.body[i]!.id!)).subscribe((body: any) => {});
        }
      }

      /*this.rc.findByCartpersiId(this.getIdPersi()).subscribe((plop: any) => {
        //this.lampService.Idcartpresi(body.body.cartpersiId);
        for (let i = 0; i < plop.body.length; i++) {
          if (value.product.id === plop.body[i]!.streetlampId!) {
            this.rc.delete(Number(plop.body[i]!.id!)).subscribe((body: any) => {});
          }
        }
      });*/
    }
  }
}
