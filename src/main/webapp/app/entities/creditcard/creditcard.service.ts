import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ICreditcard } from 'app/shared/model/creditcard.model';

type EntityResponseType = HttpResponse<ICreditcard>;
type EntityArrayResponseType = HttpResponse<ICreditcard[]>;

@Injectable({ providedIn: 'root' })
export class CreditcardService {
  public resourceUrl = SERVER_API_URL + 'api/creditcards';

  constructor(protected http: HttpClient) {}

  create(creditcard: ICreditcard): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(creditcard);
    return this.http
      .post<ICreditcard>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(creditcard: ICreditcard): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(creditcard);
    return this.http
      .put<ICreditcard>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<ICreditcard>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<ICreditcard[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(creditcard: ICreditcard): ICreditcard {
    const copy: ICreditcard = Object.assign({}, creditcard, {
      dateexpiration:
        creditcard.dateexpiration && creditcard.dateexpiration.isValid() ? creditcard.dateexpiration.format(DATE_FORMAT) : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.dateexpiration = res.body.dateexpiration ? moment(res.body.dateexpiration) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((creditcard: ICreditcard) => {
        creditcard.dateexpiration = creditcard.dateexpiration ? moment(creditcard.dateexpiration) : undefined;
      });
    }
    return res;
  }
}
