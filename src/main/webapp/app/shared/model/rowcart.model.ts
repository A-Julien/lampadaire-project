export interface IRowcart {
  id?: number;
  quantity?: number;
  streetlampId?: number;
}

export class Rowcart implements IRowcart {
  constructor(public id?: number, public quantity?: number, public streetlampId?: number) {}
}
