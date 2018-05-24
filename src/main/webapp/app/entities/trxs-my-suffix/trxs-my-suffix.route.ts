import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil } from 'ng-jhipster';

import { UserRouteAccessService } from '../../shared';
import { TrxsMySuffixComponent } from './trxs-my-suffix.component';
import { TrxsMySuffixDetailComponent } from './trxs-my-suffix-detail.component';
import { TrxsMySuffixPopupComponent } from './trxs-my-suffix-dialog.component';
import { TrxsMySuffixDeletePopupComponent } from './trxs-my-suffix-delete-dialog.component';

@Injectable()
export class TrxsMySuffixResolvePagingParams implements Resolve<any> {

    constructor(private paginationUtil: JhiPaginationUtil) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const page = route.queryParams['page'] ? route.queryParams['page'] : '1';
        const sort = route.queryParams['sort'] ? route.queryParams['sort'] : 'id,asc';
        return {
            page: this.paginationUtil.parsePage(page),
            predicate: this.paginationUtil.parsePredicate(sort),
            ascending: this.paginationUtil.parseAscending(sort)
      };
    }
}

export const trxsRoute: Routes = [
    {
        path: 'trxs-my-suffix',
        component: TrxsMySuffixComponent,
        resolve: {
            'pagingParams': TrxsMySuffixResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'mVtasApp.trxs.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'trxs-my-suffix/:id',
        component: TrxsMySuffixDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'mVtasApp.trxs.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const trxsPopupRoute: Routes = [
    {
        path: 'trxs-my-suffix-new',
        component: TrxsMySuffixPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'mVtasApp.trxs.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'trxs-my-suffix/:id/edit',
        component: TrxsMySuffixPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'mVtasApp.trxs.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'trxs-my-suffix/:id/delete',
        component: TrxsMySuffixDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'mVtasApp.trxs.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
