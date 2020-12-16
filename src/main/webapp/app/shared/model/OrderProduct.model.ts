import { IStreetlamp, Streetlamp } from './streetlamp.model';

export class ProductOrder {
  product: IStreetlamp;
  quantity: number;

  constructor(product: Streetlamp, quantity: number) {
    this.product = product;
    this.quantity = quantity;
  }
}
