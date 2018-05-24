import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { TrxsMySuffix } from './trxs-my-suffix.model';
import { TrxsMySuffixPopupService } from './trxs-my-suffix-popup.service';
import { TrxsMySuffixService } from './trxs-my-suffix.service';

@Component({
    selector: 'jhi-trxs-my-suffix-dialog',
    templateUrl: './trxs-my-suffix-dialog.component.html'
})
export class TrxsMySuffixDialogComponent implements OnInit {

    trxs: TrxsMySuffix;
    isSaving: boolean;

    constructor(
        public activeModal: NgbActiveModal,
        private trxsService: TrxsMySuffixService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.trxs.id !== undefined) {
            this.subscribeToSaveResponse(
                this.trxsService.update(this.trxs));
        } else {
            this.subscribeToSaveResponse(
                this.trxsService.create(this.trxs));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<TrxsMySuffix>>) {
        result.subscribe((res: HttpResponse<TrxsMySuffix>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: TrxsMySuffix) {
        this.eventManager.broadcast({ name: 'trxsListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }
}

@Component({
    selector: 'jhi-trxs-my-suffix-popup',
    template: ''
})
export class TrxsMySuffixPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private trxsPopupService: TrxsMySuffixPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.trxsPopupService
                    .open(TrxsMySuffixDialogComponent as Component, params['id']);
            } else {
                this.trxsPopupService
                    .open(TrxsMySuffixDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
