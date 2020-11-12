import { Streetlamp } from './streetlamp.model';

export class ProductOrder {
  product: Streetlamp;
  quantity: number;

  constructor(product: Streetlamp, quantity: number) {
    this.product = product;
    this.quantity = quantity;
  }
}
