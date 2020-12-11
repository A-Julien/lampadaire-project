import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ICartpersi } from 'app/shared/model/cartpersi.model';

type EntityResponseType = HttpResponse<ICartpersi>;
type EntityArrayResponseType = HttpResponse<ICartpersi[]>;

@Injectable({ providedIn: 'root' })
export class CartpersiService {
  public resourceUrl = SERVER_API_URL + 'api/cartpersis';

  constructor(protected http: HttpClient) {}

  create(cartpersi: ICartpersi): Observable<EntityResponseType> {
    return this.http.post<ICartpersi>(this.resourceUrl, cartpersi, { observe: 'response' });
  }

  update(cartpersi: ICartpersi): Observable<EntityResponseType> {
    return this.http.put<ICartpersi>(this.resourceUrl, cartpersi, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ICartpersi>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ICartpersi[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
