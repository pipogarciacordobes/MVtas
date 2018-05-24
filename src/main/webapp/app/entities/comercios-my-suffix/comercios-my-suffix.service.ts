import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { ComerciosMySuffix } from './comercios-my-suffix.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<ComerciosMySuffix>;

@Injectable()
export class ComerciosMySuffixService {

    private resourceUrl =  SERVER_API_URL + 'api/comercios';

    constructor(private http: HttpClient) { }

    create(comercios: ComerciosMySuffix): Observable<EntityResponseType> {
        const copy = this.convert(comercios);
        return this.http.post<ComerciosMySuffix>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(comercios: ComerciosMySuffix): Observable<EntityResponseType> {
        const copy = this.convert(comercios);
        return this.http.put<ComerciosMySuffix>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<ComerciosMySuffix>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<ComerciosMySuffix[]>> {
        const options = createRequestOption(req);
        return this.http.get<ComerciosMySuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<ComerciosMySuffix[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: ComerciosMySuffix = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<ComerciosMySuffix[]>): HttpResponse<ComerciosMySuffix[]> {
        const jsonResponse: ComerciosMySuffix[] = res.body;
        const body: ComerciosMySuffix[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to ComerciosMySuffix.
     */
    private convertItemFromServer(comercios: ComerciosMySuffix): ComerciosMySuffix {
        const copy: ComerciosMySuffix = Object.assign({}, comercios);
        return copy;
    }

    /**
     * Convert a ComerciosMySuffix to a JSON which can be sent to the server.
     */
    private convert(comercios: ComerciosMySuffix): ComerciosMySuffix {
        const copy: ComerciosMySuffix = Object.assign({}, comercios);
        return copy;
    }
}
