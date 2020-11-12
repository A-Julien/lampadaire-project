import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IStreetlamp } from 'app/shared/model/streetlamp.model';

type EntityResponseType = HttpResponse<IStreetlamp>;
type EntityArrayResponseType = HttpResponse<IStreetlamp[]>;

@Injectable({ providedIn: 'root' })
export class StreetlampService {
  public resourceUrl = SERVER_API_URL + 'api/streetlamps';

  constructor(protected http: HttpClient) {}

  create(streetlamp: IStreetlamp): Observable<EntityResponseType> {
    return this.http.post<IStreetlamp>(this.resourceUrl, streetlamp, { observe: 'response' });
  }

  update(streetlamp: IStreetlamp): Observable<EntityResponseType> {
    return this.http.put<IStreetlamp>(this.resourceUrl, streetlamp, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IStreetlamp>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IStreetlamp[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
