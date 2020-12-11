import { Moment } from 'moment';

export interface ICreditcard {
  id?: number;
  numcarte?: string;
  dateexpiration?: Moment;
  code?: string;
  applicationUserId?: number;
}

export class Creditcard implements ICreditcard {
  constructor(
    public id?: number,
    public numcarte?: string,
    public dateexpiration?: Moment,
    public code?: string,
    public applicationUserId?: number
  ) {}
}
