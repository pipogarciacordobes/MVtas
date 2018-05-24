import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { PVMySuffix } from './pv-my-suffix.model';
import { PVMySuffixService } from './pv-my-suffix.service';

@Component({
    selector: 'jhi-pv-my-suffix-detail',
    templateUrl: './pv-my-suffix-detail.component.html'
})
export class PVMySuffixDetailComponent implements OnInit, OnDestroy {

    pV: PVMySuffix;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private pVService: PVMySuffixService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInPVS();
    }

    load(id) {
        this.pVService.find(id)
            .subscribe((pVResponse: HttpResponse<PVMySuffix>) => {
                this.pV = pVResponse.body;
            });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInPVS() {
        this.eventSubscriber = this.eventManager.subscribe(
            'pVListModification',
            (response) => this.load(this.pV.id)
        );
    }
}
