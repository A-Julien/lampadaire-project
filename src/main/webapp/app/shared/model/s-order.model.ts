import { Moment } from 'moment';
import { IRoworder } from 'app/shared/model/roworder.model';

export interface ISOrder {
  id?: number;
  datecommande?: Moment;
  roworders?: IRoworder[];
  applicationUserId?: number;
}

export class SOrder implements ISOrder {
  constructor(public id?: number, public datecommande?: Moment, public roworders?: IRoworder[], public applicationUserId?: number) {}
}
