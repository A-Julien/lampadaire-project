import { Component, OnInit, OnDestroy, ViewChild, HostListener, ElementRef, AfterViewInit } from '@angular/core';
import { Subscription } from 'rxjs';

import { LoginModalService } from 'app/core/login/login-modal.service';
import { AccountService } from 'app/core/auth/account.service';
import { Account } from 'app/core/user/account.model';
import { OrdersComponent } from 'app/core/orders/orders.component';
import { ProductsPageComponent } from 'app/core/products-page/products-page.component';
import { ShoppingCartComponent } from 'app/core/shopping-cart/shopping-cart.component';

@Component({
  selector: 'jhi-catalogue',
  templateUrl: './catalogue.component.html',
  styleUrls: ['./catalogue.component.scss'],
})
export class CatalogueComponent implements OnInit, OnDestroy, AfterViewInit {
  account: Account | null = null;
  authSubscription?: Subscription;
  orderFinished = false;
  private collapsed = true;

  sticky: boolean;
  elementPosition: any;

  @ViewChild('stickyCart')
  cartElement!: ElementRef;

  @ViewChild('ordersC')
  ordersC!: OrdersComponent;

  @ViewChild('productsC')
  productsC!: ProductsPageComponent;

  @ViewChild('shoppingCartC')
  shoppingCartC!: ShoppingCartComponent;

  constructor(private accountService: AccountService, private loginModalService: LoginModalService) {
    this.sticky = false;
  }

  ngAfterViewInit(): void {
    this.elementPosition = this.cartElement.nativeElement.offsetTop;
  }

  ngOnInit(): void {
    this.authSubscription = this.accountService.getAuthenticationState().subscribe(account => (this.account = account));
  }

  isAuthenticated(): boolean {
    return this.accountService.isAuthenticated();
  }

  login(): void {
    this.loginModalService.open();
  }

  ngOnDestroy(): void {
    if (this.authSubscription) {
      this.authSubscription.unsubscribe();
    }
  }
  toggleCollapsed(): void {
    this.collapsed = !this.collapsed;
  }

  finishOrder(orderFinished: boolean): void {
    this.orderFinished = orderFinished;
  }

  @HostListener('window:scroll', ['$event'])
  handleScroll(): void {
    const windowScroll = window.pageYOffset;
    if (windowScroll >= this.elementPosition) {
      this.sticky = true;
    } else {
      this.sticky = false;
    }
  }

  reset(): void {
    this.orderFinished = false;
    this.productsC.reset();
    this.shoppingCartC.reset();
    // this.ordersC.paid = false;
  }
}
