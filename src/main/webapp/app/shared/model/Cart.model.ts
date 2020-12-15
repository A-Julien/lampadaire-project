import { ProductOrder } from './OrderProduct.model';

export class Cart {
  private _productOrders: ProductOrder[] = [];

  set productOrders(value: ProductOrder[]) {
    this._productOrders = value;
  }

  get productOrders(): ProductOrder[] {
    return this._productOrders;
  }

  public copy(cart: ProductOrder[]): void {
    this._productOrders = cart;
  }
}
