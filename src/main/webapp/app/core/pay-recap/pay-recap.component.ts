import { AfterViewInit, Component, ElementRef, HostListener, OnInit, ViewChild } from '@angular/core';
import { ProductOrder } from 'app/shared/model/OrderProduct.model';
import { IStreetlamp, Streetlamp } from 'app/shared/model/streetlamp.model';
import { Cart } from 'app/shared/model/Cart.model';
import { Subscription } from 'rxjs';
import { LampService } from 'app/core/services/lamp-service.service';
import { JhiParseLinks } from 'ng-jhipster';
import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';

@Component({
  selector: 'jhi-pay-recap',
  templateUrl: './pay-recap.component.html',
  styleUrls: ['./pay-recap.component.scss'],
})
export class PayRecapComponent implements OnInit {
  productOrders: ProductOrder[] = [];

  products: Streetlamp[] = [];
  selectedProductOrder!: ProductOrder;
  cart!: Cart;
  sub!: Subscription;
  productSelected: boolean;
  private page: number;
  private itemsPerPage: number;
  links: any;
  isListLayout: boolean;
  total: any;

  constructor(private lampService: LampService, protected parseLinks: JhiParseLinks) {
    this.itemsPerPage = ITEMS_PER_PAGE;
    this.page = 0;
    this.links = {
      last: 0,
    };
    this.productSelected = false;
    this.isListLayout = false;
  }

  ngOnInit(): void {
    this.cart = this.lampService.get();
    this.total = this.lampService.calculateTotal(this.cart.productOrders);
    this.productOrders = [];
    this.subToCart();
  }

  private subToCart(): void {
    //this.productOrders = this.lampService.ProductOrders.productOrders;
    //this.productOrders = this.lampService.getCart();
    this.sub = this.lampService.ProductOrderChanged.subscribe(() => {
      this.cart = this.lampService.get();
      this.total = this.lampService.calculateTotal(this.cart.lamps);
    });
  }

  getProductIndex(product: IStreetlamp): number {
    return this.lampService.ProductOrders.productOrders.findIndex((value: { product: Streetlamp }) => value.product === product);
  }

  addToCart(order: ProductOrder): void {
    this.lampService.SelectedProductOrder = order;
    this.selectedProductOrder = this.lampService.SelectedProductOrder;
    this.productSelected = true;
  }

  removeFromCart(lamp: ProductOrder): void {
    this.lampService.removeLamp(lamp);
    /*const index = this.getProductIndex(productOrder.product);
    if (index > -1) {
      this.productOrders.splice(this.getProductIndex(productOrder.product), 1);
    }
    this.lampService.saveCart = this.productOrders;
    this.lampService.ProductOrders.productOrders = this.productOrders; //this.shoppingCartOrders;
    this.productSelected = false;*/
  }

  trackId(index: number, item: ProductOrder): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.product.id!;
  }

  setListLayout(b: boolean): void {
    this.isListLayout = b;
  }
}
