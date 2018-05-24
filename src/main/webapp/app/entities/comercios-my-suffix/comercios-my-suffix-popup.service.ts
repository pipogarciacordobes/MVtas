import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { HttpResponse } from '@angular/common/http';
import { ComerciosMySuffix } from './comercios-my-suffix.model';
import { ComerciosMySuffixService } from './comercios-my-suffix.service';

@Injectable()
export class ComerciosMySuffixPopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private modalService: NgbModal,
        private router: Router,
        private comerciosService: ComerciosMySuffixService

    ) {
        this.ngbModalRef = null;
    }

    open(component: Component, id?: number | any): Promise<NgbModalRef> {
        return new Promise<NgbModalRef>((resolve, reject) => {
            const isOpen = this.ngbModalRef !== null;
            if (isOpen) {
                resolve(this.ngbModalRef);
            }

            if (id) {
                this.comerciosService.find(id)
                    .subscribe((comerciosResponse: HttpResponse<ComerciosMySuffix>) => {
                        const comercios: ComerciosMySuffix = comerciosResponse.body;
                        this.ngbModalRef = this.comerciosModalRef(component, comercios);
                        resolve(this.ngbModalRef);
                    });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    this.ngbModalRef = this.comerciosModalRef(component, new ComerciosMySuffix());
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    comerciosModalRef(component: Component, comercios: ComerciosMySuffix): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.comercios = comercios;
        modalRef.result.then((result) => {
            this.router.navigate([{ outlets: { popup: null }}], { replaceUrl: true, queryParamsHandling: 'merge' });
            this.ngbModalRef = null;
        }, (reason) => {
            this.router.navigate([{ outlets: { popup: null }}], { replaceUrl: true, queryParamsHandling: 'merge' });
            this.ngbModalRef = null;
        });
        return modalRef;
    }
}
