import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MVtasSharedModule } from '../../shared';
import {
    PVMySuffixService,
    PVMySuffixPopupService,
    PVMySuffixComponent,
    PVMySuffixDetailComponent,
    PVMySuffixDialogComponent,
    PVMySuffixPopupComponent,
    PVMySuffixDeletePopupComponent,
    PVMySuffixDeleteDialogComponent,
    pVRoute,
    pVPopupRoute,
    PVMySuffixResolvePagingParams,
} from './';

const ENTITY_STATES = [
    ...pVRoute,
    ...pVPopupRoute,
];

@NgModule({
    imports: [
        MVtasSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        PVMySuffixComponent,
        PVMySuffixDetailComponent,
        PVMySuffixDialogComponent,
        PVMySuffixDeleteDialogComponent,
        PVMySuffixPopupComponent,
        PVMySuffixDeletePopupComponent,
    ],
    entryComponents: [
        PVMySuffixComponent,
        PVMySuffixDialogComponent,
        PVMySuffixPopupComponent,
        PVMySuffixDeleteDialogComponent,
        PVMySuffixDeletePopupComponent,
    ],
    providers: [
        PVMySuffixService,
        PVMySuffixPopupService,
        PVMySuffixResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class MVtasPVMySuffixModule {}
