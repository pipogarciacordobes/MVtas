/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Observable';
import { JhiEventManager } from 'ng-jhipster';

import { MVtasTestModule } from '../../../test.module';
import { TrxsMySuffixDeleteDialogComponent } from '../../../../../../main/webapp/app/entities/trxs-my-suffix/trxs-my-suffix-delete-dialog.component';
import { TrxsMySuffixService } from '../../../../../../main/webapp/app/entities/trxs-my-suffix/trxs-my-suffix.service';

describe('Component Tests', () => {

    describe('TrxsMySuffix Management Delete Component', () => {
        let comp: TrxsMySuffixDeleteDialogComponent;
        let fixture: ComponentFixture<TrxsMySuffixDeleteDialogComponent>;
        let service: TrxsMySuffixService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [MVtasTestModule],
                declarations: [TrxsMySuffixDeleteDialogComponent],
                providers: [
                    TrxsMySuffixService
                ]
            })
            .overrideTemplate(TrxsMySuffixDeleteDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(TrxsMySuffixDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(TrxsMySuffixService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('confirmDelete', () => {
            it('Should call delete service on confirmDelete',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        spyOn(service, 'delete').and.returnValue(Observable.of({}));

                        // WHEN
                        comp.confirmDelete(123);
                        tick();

                        // THEN
                        expect(service.delete).toHaveBeenCalledWith(123);
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
                    })
                )
            );
        });
    });

});
