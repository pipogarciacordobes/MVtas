import { BaseEntity } from './../../shared';

export class ComerciosMySuffix implements BaseEntity {
    constructor(
        public id?: number,
        public comerciosName?: string,
        public pVId?: number,
    ) {
    }
}
