import { Injectable } from '@angular/core';
import { ProductOrder } from '../../shared/model/OrderProduct.model';
import { Cart } from '../../shared/model/Cart.model';
import { Subject } from 'rxjs/internal/Subject';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

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

  ProductOrderChanged = this.productOrderSubject.asObservable();
  OrdersChanged = this.ordersSubject.asObservable();
  TotalChanged = this.totalSubject.asObservable();

  constructor(private http: HttpClient) {
    this.total = 0;
  }

  getAllProducts(): Observable<any> {
    return this.http.get(this.productsUrl);
  }

  saveOrder(order: Cart): Observable<any> {
    return this.http.post(this.ordersUrl, order);
  }

  set SelectedProductOrder(value: ProductOrder) {
    this.productOrder = value;
    this.productOrderSubject.next();
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
