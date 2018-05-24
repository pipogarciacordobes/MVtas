import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { ComerciosMySuffix } from './comercios-my-suffix.model';
import { ComerciosMySuffixPopupService } from './comercios-my-suffix-popup.service';
import { ComerciosMySuffixService } from './comercios-my-suffix.service';
import { PVMySuffix, PVMySuffixService } from '../pv-my-suffix';

@Component({
    selector: 'jhi-comercios-my-suffix-dialog',
    templateUrl: './comercios-my-suffix-dialog.component.html'
})
export class ComerciosMySuffixDialogComponent implements OnInit {

    comercios: ComerciosMySuffix;
    isSaving: boolean;

    pvs: PVMySuffix[];

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private comerciosService: ComerciosMySuffixService,
        private pVService: PVMySuffixService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.pVService.query()
            .subscribe((res: HttpResponse<PVMySuffix[]>) => { this.pvs = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.comercios.id !== undefined) {
            this.subscribeToSaveResponse(
                this.comerciosService.update(this.comercios));
        } else {
            this.subscribeToSaveResponse(
                this.comerciosService.create(this.comercios));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<ComerciosMySuffix>>) {
        result.subscribe((res: HttpResponse<ComerciosMySuffix>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: ComerciosMySuffix) {
        this.eventManager.broadcast({ name: 'comerciosListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(error: any) {
        this.jhiAlertService.error(error.message, null, null);
    }

    trackPVById(index: number, item: PVMySuffix) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-comercios-my-suffix-popup',
    template: ''
})
export class ComerciosMySuffixPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private comerciosPopupService: ComerciosMySuffixPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.comerciosPopupService
                    .open(ComerciosMySuffixDialogComponent as Component, params['id']);
            } else {
                this.comerciosPopupService
                    .open(ComerciosMySuffixDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
