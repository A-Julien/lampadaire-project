export interface IRowcart {
  id?: number;
  quantity?: number;
  streetlampId?: number;
  cartpersiId?: number;
}

export class Rowcart implements IRowcart {
  constructor(public id?: number, public quantity?: number, public streetlampId?: number, public cartpersiId?: number) {}
}
