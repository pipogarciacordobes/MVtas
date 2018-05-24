import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { PVMySuffix } from './pv-my-suffix.model';
import { PVMySuffixPopupService } from './pv-my-suffix-popup.service';
import { PVMySuffixService } from './pv-my-suffix.service';

@Component({
    selector: 'jhi-pv-my-suffix-delete-dialog',
    templateUrl: './pv-my-suffix-delete-dialog.component.html'
})
export class PVMySuffixDeleteDialogComponent {

    pV: PVMySuffix;

    constructor(
        private pVService: PVMySuffixService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.pVService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'pVListModification',
                content: 'Deleted an pV'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-pv-my-suffix-delete-popup',
    template: ''
})
export class PVMySuffixDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private pVPopupService: PVMySuffixPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.pVPopupService
                .open(PVMySuffixDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
