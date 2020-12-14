import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IRowcart } from 'app/shared/model/rowcart.model';

type EntityResponseType = HttpResponse<IRowcart>;
type EntityArrayResponseType = HttpResponse<IRowcart[]>;

@Injectable({ providedIn: 'root' })
export class RowcartService {
  public resourceUrl = SERVER_API_URL + 'api/rowcarts';

  constructor(protected http: HttpClient) {}

  create(rowcart: IRowcart): Observable<EntityResponseType> {
    return this.http.post<IRowcart>(this.resourceUrl, rowcart, { observe: 'response' });
  }

  update(rowcart: IRowcart): Observable<EntityResponseType> {
    return this.http.put<IRowcart>(this.resourceUrl, rowcart, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IRowcart>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  findByCartpersiId(id: number): Observable<HttpResponse<IRowcart[]>> {
    return this.http
      .get<IRowcart[]>(`${this.resourceUrl}/cartpersi/${id}`, { observe: 'response' })
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IRowcart[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
