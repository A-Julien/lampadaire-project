import { Component, EventEmitter, OnDestroy, OnInit, Output } from '@angular/core';
import { ProductOrder } from 'app/shared/model/OrderProduct.model';
import { Cart } from 'app/shared/model/Cart.model';
import { Subscription } from 'rxjs';
import { LampService } from 'app/core/services/lamp-service.service';

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

  @Output() onOrderFinished: EventEmitter<boolean>;

  constructor(private lampService: LampService) {
    this.total = 0;
    this.orderFinished = false;
    this.onOrderFinished = new EventEmitter<boolean>();
  }

  ngOnInit(): void {
    this.orders = new Cart();
    this.loadCart();
    this.loadTotal();
  }

  loadTotal(): void {
    this.sub = this.lampService.OrdersChanged.subscribe(() => {
      this.total = this.calculateTotal(this.orders.productOrders);
    });
  }

  private calculateTotal(products: ProductOrder[]): number {
    /*let sum = 0;
    products.forEach(value => {
      sum += (value.product.price * value.quantity);
    });*/
    return 0;
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
    console.log(this.orders);
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
