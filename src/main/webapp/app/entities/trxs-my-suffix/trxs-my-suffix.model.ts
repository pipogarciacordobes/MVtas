import { BaseEntity } from './../../shared';

export class TrxsMySuffix implements BaseEntity {
    constructor(
        public id?: number,
        public pvData?: string,
        public idPV?: number,
        public idPVS?: BaseEntity[],
    ) {
    }
}
