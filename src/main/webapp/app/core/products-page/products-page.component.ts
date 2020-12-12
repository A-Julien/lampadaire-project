import { Component, OnInit } from '@angular/core';
import { IStreetlamp, Streetlamp } from 'app/shared/model/streetlamp.model';
import { ProductOrder } from 'app/shared/model/OrderProduct.model';
import { Cart } from 'app/shared/model/Cart.model';
import { Subscription } from 'rxjs';
import { LampService } from 'app/core/services/lamp-service.service';
import { StreetlampService } from 'app/entities/streetlamp/streetlamp.service';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { JhiParseLinks } from 'ng-jhipster';

@Component({
  selector: 'jhi-products-page',
  templateUrl: './products-page.component.html',
  styleUrls: ['./products-page.component.scss'],
})
export class ProductsPageComponent implements OnInit {
  productOrders: ProductOrder[] = [];
  products: Streetlamp[] = [];
  selectedProductOrder!: ProductOrder;
  private shoppingCartOrders!: Cart;
  sub!: Subscription;
  productSelected: boolean;
  private page: number;
  private itemsPerPage: number;
  links: any;
  isListLayout: boolean;

  constructor(private lampService: LampService, private streetLampService: StreetlampService, protected parseLinks: JhiParseLinks) {
    this.itemsPerPage = ITEMS_PER_PAGE;
    this.page = 0;
    this.links = {
      last: 0,
    };
    this.productSelected = false;
    this.isListLayout = false;
  }

  ngOnInit(): void {
    this.productOrders = [];
    this.loadProducts();
    this.loadOrders();
  }

  loadProducts(): void {
    this.streetLampService
      .query({
        page: this.page,
        size: this.itemsPerPage,
      })
      .subscribe((res: HttpResponse<IStreetlamp[]>) => this.paginateStreetlamps(res.body, res.headers));
  }
  protected paginateStreetlamps(data: IStreetlamp[] | null, headers: HttpHeaders): void {
    const headersLink = headers.get('link');
    this.links = this.parseLinks.parse(headersLink ? headersLink : '');
    if (data) {
      for (let i = 0; i < data.length; i++) {
        this.productOrders.push(new ProductOrder(data[i], 0));
      }
    }
  }

  getProductIndex(product: IStreetlamp): number {
    return this.lampService.ProductOrders.productOrders.findIndex((value: { product: Streetlamp }) => value.product === product);
  }

  loadOrders(): void {
    this.sub = this.lampService.OrdersChanged.subscribe(() => {
      this.shoppingCartOrders = this.lampService.ProductOrders;
    });
  }

  addToCart(order: ProductOrder): void {
    this.lampService.SelectedProductOrder = order;
    this.selectedProductOrder = this.lampService.SelectedProductOrder;
    this.productSelected = true;
  }

  removeFromCart(productOrder: ProductOrder): void {
    const index = this.getProductIndex(productOrder.product);
    if (index > -1) {
      this.shoppingCartOrders.productOrders.splice(this.getProductIndex(productOrder.product), 1);
    }
    this.lampService.ProductOrders = this.shoppingCartOrders;
    this.shoppingCartOrders = this.lampService.ProductOrders;
    this.productSelected = false;
  }

  isProductSelected(product: Streetlamp): boolean {
    return this.getProductIndex(product) > -1;
  }

  reset(): void {
    this.productOrders = [];
    this.loadProducts();
    this.lampService.ProductOrders.productOrders = [];
    this.loadOrders();
    this.productSelected = false;
  }

  setListLayout(b: boolean): void {
    this.isListLayout = b;
  }
}
