export interface IRoworder {
  id?: number;
  price?: number;
  quantite?: number;
  streetlampId?: number;
}

export class Roworder implements IRoworder {
  constructor(public id?: number, public price?: number, public quantite?: number, public streetlampId?: number) {}
}
