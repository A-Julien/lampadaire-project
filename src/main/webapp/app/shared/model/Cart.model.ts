import { ProductOrder } from './OrderProduct.model';
import { IStreetlamp } from 'app/shared/model/streetlamp.model';

export class Cart {
  private _productOrders: ProductOrder[] = [];
  public lamps: Array<ProductOrder>;

  constructor() {
    this.lamps = new Array<ProductOrder>();
  }

  set productOrders(value: ProductOrder[]) {
    this._productOrders = value;
  }
  get productOrders(): ProductOrder[] {
    return this._productOrders;
  }

  addLamp(lamp: ProductOrder): void {
    //this.productOrders.push(lamp);
    this.lamps.push(lamp);
  }

  removeLamp(lamp: ProductOrder): void {
    let index = -1;
    this.lamps.forEach((l, i) => {
      if (l.product.id === lamp.product.id) index = i;
    });

    if (index !== -1) {
      this.lamps.splice(index, 1);
    }
  }

  /*get lamps(): Array<ProductOrder> {
    return this._lamps;
  }*/

  public cp(cart: Cart): void {
    if (cart.lamps !== undefined) {
      this.lamps = cart.lamps;
    }
  }

  public copy(cart: ProductOrder[]): void {
    this._productOrders = cart;
  }
}
