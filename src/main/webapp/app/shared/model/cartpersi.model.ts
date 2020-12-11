import { IRowcart } from 'app/shared/model/rowcart.model';

export interface ICartpersi {
  id?: number;
  rowcarts?: IRowcart[];
}

export class Cartpersi implements ICartpersi {
  constructor(public id?: number, public rowcarts?: IRowcart[]) {}
}
