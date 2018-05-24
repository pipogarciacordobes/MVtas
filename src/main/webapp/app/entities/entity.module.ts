import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { MVtasComerciosMySuffixModule } from './comercios-my-suffix/comercios-my-suffix.module';
import { MVtasPVMySuffixModule } from './pv-my-suffix/pv-my-suffix.module';
import { MVtasTrxsMySuffixModule } from './trxs-my-suffix/trxs-my-suffix.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    imports: [
        MVtasComerciosMySuffixModule,
        MVtasPVMySuffixModule,
        MVtasTrxsMySuffixModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class MVtasEntityModule {}
