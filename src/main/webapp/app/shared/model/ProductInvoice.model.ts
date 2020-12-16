export class Product {
  name: string;
  price: number;
  qty: number;

  constructor(name: string, price: number, qty: number) {
    this.name = name;
    this.price = price;
    this.qty = qty;
  }
}
