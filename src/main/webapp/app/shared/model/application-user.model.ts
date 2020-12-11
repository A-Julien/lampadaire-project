import { ISOrder } from 'app/shared/model/s-order.model';
import { ICreditcard } from 'app/shared/model/creditcard.model';

export interface IApplicationUser {
  id?: number;
  siret?: string;
  userLogin?: string;
  userId?: number;
  sorders?: ISOrder[];
  creditcards?: ICreditcard[];
  cartpersiId?: number;
}

export class ApplicationUser implements IApplicationUser {
  constructor(
    public id?: number,
    public siret?: string,
    public userLogin?: string,
    public userId?: number,
    public sorders?: ISOrder[],
    public creditcards?: ICreditcard[],
    public cartpersiId?: number
  ) {}
}
