import { Injectable } from '@angular/core';
import { ProductOrder } from '../../shared/model/OrderProduct.model';
import { Cart } from '../../shared/model/Cart.model';
import { Subject } from 'rxjs/internal/Subject';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { RowcartService } from 'app/entities/rowcart/rowcart.service';
import {Rowcart} from 'app/shared/model/rowcart.model';

@Injectable({
  providedIn: 'root',
})
export class LampService {
  private productsUrl = '/api/streetlamps';
  private ordersUrl = '/api/orders';

  private productOrder!: ProductOrder;
  private orders: Cart = new Cart();

  private productOrderSubject = new Subject();
  private ordersSubject = new Subject();
  private totalSubject = new Subject();

  private total: number;
  private idcartpresi: number;

  ProductOrderChanged = this.productOrderSubject.asObservable();
  OrdersChanged = this.ordersSubject.asObservable();
  TotalChanged = this.totalSubject.asObservable();

  constructor(private http: HttpClient,private rc :RowcartService) {
    this.total = 0;
    this.idcartpresi = -1;
  }

  getAllProducts(): Observable<any> {
    return this.http.get(this.productsUrl);
  }

  saveOrder(order: Cart): Observable<any> {
    return this.http.post(this.ordersUrl, order);
  }

  set Idcartpresi(id:any){
    this.idcartpresi=id;
  }

  set SelectedProductOrder(value: ProductOrder) {
    this.productOrder = value;
    this.productOrderSubject.next();
    if (this.idcartpresi!==-1) {
      this.rc.create(new Rowcart(undefined, value.quantity, value.product.id, this.idcartpresi)).subscribe((body: any) => {});
    }
  }

  get SelectedProductOrder(): ProductOrder {
    return this.productOrder;
  }

  set ProductOrders(value: Cart) {
    this.orders = value;
    this.ordersSubject.next();
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
}
