import { Routes } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { ComerciosMySuffixComponent } from './comercios-my-suffix.component';
import { ComerciosMySuffixDetailComponent } from './comercios-my-suffix-detail.component';
import { ComerciosMySuffixPopupComponent } from './comercios-my-suffix-dialog.component';
import { ComerciosMySuffixDeletePopupComponent } from './comercios-my-suffix-delete-dialog.component';

export const comerciosRoute: Routes = [
    {
        path: 'comercios-my-suffix',
        component: ComerciosMySuffixComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'mVtasApp.comercios.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'comercios-my-suffix/:id',
        component: ComerciosMySuffixDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'mVtasApp.comercios.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const comerciosPopupRoute: Routes = [
    {
        path: 'comercios-my-suffix-new',
        component: ComerciosMySuffixPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'mVtasApp.comercios.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'comercios-my-suffix/:id/edit',
        component: ComerciosMySuffixPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'mVtasApp.comercios.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'comercios-my-suffix/:id/delete',
        component: ComerciosMySuffixDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'mVtasApp.comercios.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
