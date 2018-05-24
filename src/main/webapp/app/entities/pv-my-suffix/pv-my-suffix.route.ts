import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil } from 'ng-jhipster';

import { UserRouteAccessService } from '../../shared';
import { PVMySuffixComponent } from './pv-my-suffix.component';
import { PVMySuffixDetailComponent } from './pv-my-suffix-detail.component';
import { PVMySuffixPopupComponent } from './pv-my-suffix-dialog.component';
import { PVMySuffixDeletePopupComponent } from './pv-my-suffix-delete-dialog.component';

@Injectable()
export class PVMySuffixResolvePagingParams implements Resolve<any> {

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

export const pVRoute: Routes = [
    {
        path: 'pv-my-suffix',
        component: PVMySuffixComponent,
        resolve: {
            'pagingParams': PVMySuffixResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'mVtasApp.pV.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'pv-my-suffix/:id',
        component: PVMySuffixDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'mVtasApp.pV.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const pVPopupRoute: Routes = [
    {
        path: 'pv-my-suffix-new',
        component: PVMySuffixPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'mVtasApp.pV.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'pv-my-suffix/:id/edit',
        component: PVMySuffixPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'mVtasApp.pV.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'pv-my-suffix/:id/delete',
        component: PVMySuffixDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'mVtasApp.pV.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
