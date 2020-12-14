import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ISOrder } from 'app/shared/model/s-order.model';

type EntityResponseType = HttpResponse<ISOrder>;
type EntityArrayResponseType = HttpResponse<ISOrder[]>;

@Injectable({ providedIn: 'root' })
export class SOrderService {
  public resourceUrl = SERVER_API_URL + 'api/s-orders';

  constructor(protected http: HttpClient) {}

  create(sOrder: ISOrder): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(sOrder);
    return this.http
      .post<ISOrder>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(sOrder: ISOrder): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(sOrder);
    return this.http
      .put<ISOrder>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<ISOrder>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<ISOrder[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(sOrder: ISOrder): ISOrder {
    const copy: ISOrder = Object.assign({}, sOrder, {
      datecommande: sOrder.datecommande && sOrder.datecommande.isValid() ? sOrder.datecommande.format(DATE_FORMAT) : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.datecommande = res.body.datecommande ? moment(res.body.datecommande) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((sOrder: ISOrder) => {
        sOrder.datecommande = sOrder.datecommande ? moment(sOrder.datecommande) : undefined;
      });
    }
    return res;
  }
}
