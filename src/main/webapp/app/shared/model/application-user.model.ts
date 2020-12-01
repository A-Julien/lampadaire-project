import { ISOrder } from 'app/shared/model/s-order.model';

export interface IApplicationUser {
  id?: number;
  siret?: string;
  userLogin?: string;
  userId?: number;
  sorders?: ISOrder[];
}

export class ApplicationUser implements IApplicationUser {
  constructor(public id?: number, public siret?: string, public userLogin?: string, public userId?: number, public sorders?: ISOrder[]) {}
}
