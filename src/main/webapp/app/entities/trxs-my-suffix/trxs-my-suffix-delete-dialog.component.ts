import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { TrxsMySuffix } from './trxs-my-suffix.model';
import { TrxsMySuffixPopupService } from './trxs-my-suffix-popup.service';
import { TrxsMySuffixService } from './trxs-my-suffix.service';

@Component({
    selector: 'jhi-trxs-my-suffix-delete-dialog',
    templateUrl: './trxs-my-suffix-delete-dialog.component.html'
})
export class TrxsMySuffixDeleteDialogComponent {

    trxs: TrxsMySuffix;

    constructor(
        private trxsService: TrxsMySuffixService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.trxsService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'trxsListModification',
                content: 'Deleted an trxs'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-trxs-my-suffix-delete-popup',
    template: ''
})
export class TrxsMySuffixDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private trxsPopupService: TrxsMySuffixPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.trxsPopupService
                .open(TrxsMySuffixDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
