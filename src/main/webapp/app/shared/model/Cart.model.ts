import { ProductOrder } from './OrderProduct.model';

export class Cart {
  productOrders: ProductOrder[] = [];

  public copy(cart: ProductOrder[]): void {
    this.productOrders = cart;
  }
}
