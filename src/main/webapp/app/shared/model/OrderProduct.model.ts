import { IStreetlamp } from './streetlamp.model';

export class ProductOrder {
  product: IStreetlamp;
  quantity: number;

  constructor(product: IStreetlamp, quantity: number) {
    this.product = product;
    this.quantity = quantity;
  }
}
