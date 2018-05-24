import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MVtasSharedModule } from '../../shared';
import {
    TrxsMySuffixService,
    TrxsMySuffixPopupService,
    TrxsMySuffixComponent,
    TrxsMySuffixDetailComponent,
    TrxsMySuffixDialogComponent,
    TrxsMySuffixPopupComponent,
    TrxsMySuffixDeletePopupComponent,
    TrxsMySuffixDeleteDialogComponent,
    trxsRoute,
    trxsPopupRoute,
    TrxsMySuffixResolvePagingParams,
} from './';

const ENTITY_STATES = [
    ...trxsRoute,
    ...trxsPopupRoute,
];

@NgModule({
    imports: [
        MVtasSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        TrxsMySuffixComponent,
        TrxsMySuffixDetailComponent,
        TrxsMySuffixDialogComponent,
        TrxsMySuffixDeleteDialogComponent,
        TrxsMySuffixPopupComponent,
        TrxsMySuffixDeletePopupComponent,
    ],
    entryComponents: [
        TrxsMySuffixComponent,
        TrxsMySuffixDialogComponent,
        TrxsMySuffixPopupComponent,
        TrxsMySuffixDeleteDialogComponent,
        TrxsMySuffixDeletePopupComponent,
    ],
    providers: [
        TrxsMySuffixService,
        TrxsMySuffixPopupService,
        TrxsMySuffixResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class MVtasTrxsMySuffixModule {}
