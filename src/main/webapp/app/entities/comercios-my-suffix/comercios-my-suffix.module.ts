import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MVtasSharedModule } from '../../shared';
import {
    ComerciosMySuffixService,
    ComerciosMySuffixPopupService,
    ComerciosMySuffixComponent,
    ComerciosMySuffixDetailComponent,
    ComerciosMySuffixDialogComponent,
    ComerciosMySuffixPopupComponent,
    ComerciosMySuffixDeletePopupComponent,
    ComerciosMySuffixDeleteDialogComponent,
    comerciosRoute,
    comerciosPopupRoute,
} from './';

const ENTITY_STATES = [
    ...comerciosRoute,
    ...comerciosPopupRoute,
];

@NgModule({
    imports: [
        MVtasSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        ComerciosMySuffixComponent,
        ComerciosMySuffixDetailComponent,
        ComerciosMySuffixDialogComponent,
        ComerciosMySuffixDeleteDialogComponent,
        ComerciosMySuffixPopupComponent,
        ComerciosMySuffixDeletePopupComponent,
    ],
    entryComponents: [
        ComerciosMySuffixComponent,
        ComerciosMySuffixDialogComponent,
        ComerciosMySuffixPopupComponent,
        ComerciosMySuffixDeleteDialogComponent,
        ComerciosMySuffixDeletePopupComponent,
    ],
    providers: [
        ComerciosMySuffixService,
        ComerciosMySuffixPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class MVtasComerciosMySuffixModule {}
