import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ComerciosMySuffix } from './comercios-my-suffix.model';
import { ComerciosMySuffixPopupService } from './comercios-my-suffix-popup.service';
import { ComerciosMySuffixService } from './comercios-my-suffix.service';

@Component({
    selector: 'jhi-comercios-my-suffix-delete-dialog',
    templateUrl: './comercios-my-suffix-delete-dialog.component.html'
})
export class ComerciosMySuffixDeleteDialogComponent {

    comercios: ComerciosMySuffix;

    constructor(
        private comerciosService: ComerciosMySuffixService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.comerciosService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'comerciosListModification',
                content: 'Deleted an comercios'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-comercios-my-suffix-delete-popup',
    template: ''
})
export class ComerciosMySuffixDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private comerciosPopupService: ComerciosMySuffixPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.comerciosPopupService
                .open(ComerciosMySuffixDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
