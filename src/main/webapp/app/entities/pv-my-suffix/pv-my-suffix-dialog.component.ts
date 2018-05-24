import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { PVMySuffix } from './pv-my-suffix.model';
import { PVMySuffixPopupService } from './pv-my-suffix-popup.service';
import { PVMySuffixService } from './pv-my-suffix.service';
import { TrxsMySuffix, TrxsMySuffixService } from '../trxs-my-suffix';

@Component({
    selector: 'jhi-pv-my-suffix-dialog',
    templateUrl: './pv-my-suffix-dialog.component.html'
})
export class PVMySuffixDialogComponent implements OnInit {

    pV: PVMySuffix;
    isSaving: boolean;

    trxs: TrxsMySuffix[];

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private pVService: PVMySuffixService,
        private trxsService: TrxsMySuffixService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.trxsService.query()
            .subscribe((res: HttpResponse<TrxsMySuffix[]>) => { this.trxs = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.pV.id !== undefined) {
            this.subscribeToSaveResponse(
                this.pVService.update(this.pV));
        } else {
            this.subscribeToSaveResponse(
                this.pVService.create(this.pV));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<PVMySuffix>>) {
        result.subscribe((res: HttpResponse<PVMySuffix>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: PVMySuffix) {
        this.eventManager.broadcast({ name: 'pVListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(error: any) {
        this.jhiAlertService.error(error.message, null, null);
    }

    trackTrxsById(index: number, item: TrxsMySuffix) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-pv-my-suffix-popup',
    template: ''
})
export class PVMySuffixPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private pVPopupService: PVMySuffixPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.pVPopupService
                    .open(PVMySuffixDialogComponent as Component, params['id']);
            } else {
                this.pVPopupService
                    .open(PVMySuffixDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
