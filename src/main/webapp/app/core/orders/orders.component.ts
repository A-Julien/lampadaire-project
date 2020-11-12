import { Component, OnInit } from '@angular/core';
import { Cart } from 'app/shared/model/Cart.model';
import { Subscription } from 'rxjs';
import { LampService } from 'app/core/services/lamp-service.service';

@Component({
  selector: 'jhi-orders',
  templateUrl: './orders.component.html',
  styleUrls: ['./orders.component.scss'],
})
export class OrdersComponent implements OnInit {
  orders: Cart;
  total: number;
  paid: boolean;
  sub!: Subscription;

  constructor(private lampService: LampService) {
    this.orders = this.lampService.ProductOrders;
    this.total = 0;
    this.paid = false;
  }

  ngOnInit(): void {
    this.paid = false;
    this.sub = this.lampService.OrdersChanged.subscribe(() => {
      this.orders = this.lampService.ProductOrders;
    });
    this.loadTotal();
  }

  loadTotal(): void {
    this.sub = this.lampService.TotalChanged.subscribe(() => {
      this.total = this.lampService.Total;
    });
  }

  pay(): void {
    this.paid = true;
    this.lampService.saveOrder(this.orders).subscribe();
  }
}
