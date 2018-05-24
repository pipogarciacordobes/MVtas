import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { ComerciosMySuffix } from './comercios-my-suffix.model';
import { ComerciosMySuffixService } from './comercios-my-suffix.service';

@Component({
    selector: 'jhi-comercios-my-suffix-detail',
    templateUrl: './comercios-my-suffix-detail.component.html'
})
export class ComerciosMySuffixDetailComponent implements OnInit, OnDestroy {

    comercios: ComerciosMySuffix;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private comerciosService: ComerciosMySuffixService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInComercios();
    }

    load(id) {
        this.comerciosService.find(id)
            .subscribe((comerciosResponse: HttpResponse<ComerciosMySuffix>) => {
                this.comercios = comerciosResponse.body;
            });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInComercios() {
        this.eventSubscriber = this.eventManager.subscribe(
            'comerciosListModification',
            (response) => this.load(this.comercios.id)
        );
    }
}
