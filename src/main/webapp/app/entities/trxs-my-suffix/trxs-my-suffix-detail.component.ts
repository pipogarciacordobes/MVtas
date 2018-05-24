import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { TrxsMySuffix } from './trxs-my-suffix.model';
import { TrxsMySuffixService } from './trxs-my-suffix.service';

@Component({
    selector: 'jhi-trxs-my-suffix-detail',
    templateUrl: './trxs-my-suffix-detail.component.html'
})
export class TrxsMySuffixDetailComponent implements OnInit, OnDestroy {

    trxs: TrxsMySuffix;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private trxsService: TrxsMySuffixService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInTrxs();
    }

    load(id) {
        this.trxsService.find(id)
            .subscribe((trxsResponse: HttpResponse<TrxsMySuffix>) => {
                this.trxs = trxsResponse.body;
            });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInTrxs() {
        this.eventSubscriber = this.eventManager.subscribe(
            'trxsListModification',
            (response) => this.load(this.trxs.id)
        );
    }
}
