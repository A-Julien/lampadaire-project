import { Product } from './ProductInvoice.model';

export class Invoice {
  public customerName: string | undefined;
  public address: string | undefined;
  public contactNo: number | undefined;
  public email: string | undefined;

  products: Product[] = [];
  additionalDetails: string | undefined;

  constructor() {
    // Initially one empty product row we will show
    //this.products.push(new Product());
  }
}
