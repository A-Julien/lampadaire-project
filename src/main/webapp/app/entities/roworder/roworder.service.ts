import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IRoworder } from 'app/shared/model/roworder.model';
import {ISOrder} from "../../shared/model/s-order.model";
import {map} from "rxjs/operators";

type EntityResponseType = HttpResponse<IRoworder>;
type EntityArrayResponseType = HttpResponse<IRoworder[]>;

@Injectable({ providedIn: 'root' })
export class RoworderService {
  public resourceUrl = SERVER_API_URL + 'api/roworders';

  constructor(protected http: HttpClient) {}

  create(roworder: IRoworder): Observable<EntityResponseType> {
    return this.http.post<IRoworder>(this.resourceUrl, roworder, { observe: 'response' });
  }

  update(roworder: IRoworder): Observable<EntityResponseType> {
    return this.http.put<IRoworder>(this.resourceUrl, roworder, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IRoworder>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  findBySOrderId(id: number): Observable<HttpResponse<ISOrder[]>> {
    return this.http
      .get<ISOrder[]>(`${this.resourceUrl}/SOrderid/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IRoworder[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
