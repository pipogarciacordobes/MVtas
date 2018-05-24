import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { PVMySuffix } from './pv-my-suffix.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<PVMySuffix>;

@Injectable()
export class PVMySuffixService {

    private resourceUrl =  SERVER_API_URL + 'api/pvs';

    constructor(private http: HttpClient) { }

    create(pV: PVMySuffix): Observable<EntityResponseType> {
        const copy = this.convert(pV);
        return this.http.post<PVMySuffix>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(pV: PVMySuffix): Observable<EntityResponseType> {
        const copy = this.convert(pV);
        return this.http.put<PVMySuffix>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<PVMySuffix>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<PVMySuffix[]>> {
        const options = createRequestOption(req);
        return this.http.get<PVMySuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<PVMySuffix[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: PVMySuffix = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<PVMySuffix[]>): HttpResponse<PVMySuffix[]> {
        const jsonResponse: PVMySuffix[] = res.body;
        const body: PVMySuffix[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to PVMySuffix.
     */
    private convertItemFromServer(pV: PVMySuffix): PVMySuffix {
        const copy: PVMySuffix = Object.assign({}, pV);
        return copy;
    }

    /**
     * Convert a PVMySuffix to a JSON which can be sent to the server.
     */
    private convert(pV: PVMySuffix): PVMySuffix {
        const copy: PVMySuffix = Object.assign({}, pV);
        return copy;
    }
}
