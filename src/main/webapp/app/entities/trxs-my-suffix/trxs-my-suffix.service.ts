import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { TrxsMySuffix } from './trxs-my-suffix.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<TrxsMySuffix>;

@Injectable()
export class TrxsMySuffixService {

    private resourceUrl =  SERVER_API_URL + 'api/trxs';

    constructor(private http: HttpClient) { }

    create(trxs: TrxsMySuffix): Observable<EntityResponseType> {
        const copy = this.convert(trxs);
        return this.http.post<TrxsMySuffix>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(trxs: TrxsMySuffix): Observable<EntityResponseType> {
        const copy = this.convert(trxs);
        return this.http.put<TrxsMySuffix>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<TrxsMySuffix>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<TrxsMySuffix[]>> {
        const options = createRequestOption(req);
        return this.http.get<TrxsMySuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<TrxsMySuffix[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: TrxsMySuffix = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<TrxsMySuffix[]>): HttpResponse<TrxsMySuffix[]> {
        const jsonResponse: TrxsMySuffix[] = res.body;
        const body: TrxsMySuffix[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to TrxsMySuffix.
     */
    private convertItemFromServer(trxs: TrxsMySuffix): TrxsMySuffix {
        const copy: TrxsMySuffix = Object.assign({}, trxs);
        return copy;
    }

    /**
     * Convert a TrxsMySuffix to a JSON which can be sent to the server.
     */
    private convert(trxs: TrxsMySuffix): TrxsMySuffix {
        const copy: TrxsMySuffix = Object.assign({}, trxs);
        return copy;
    }
}
