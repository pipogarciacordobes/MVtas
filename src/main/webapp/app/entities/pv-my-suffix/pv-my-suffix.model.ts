import { BaseEntity } from './../../shared';

export class PVMySuffix implements BaseEntity {
    constructor(
        public id?: number,
        public pvNumero?: string,
        public idComercio?: number,
        public idComercios?: BaseEntity[],
        public trxsId?: number,
    ) {
    }
}
