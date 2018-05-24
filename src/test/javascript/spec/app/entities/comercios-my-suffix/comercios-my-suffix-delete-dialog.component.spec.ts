/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Observable';
import { JhiEventManager } from 'ng-jhipster';

import { MVtasTestModule } from '../../../test.module';
import { ComerciosMySuffixDeleteDialogComponent } from '../../../../../../main/webapp/app/entities/comercios-my-suffix/comercios-my-suffix-delete-dialog.component';
import { ComerciosMySuffixService } from '../../../../../../main/webapp/app/entities/comercios-my-suffix/comercios-my-suffix.service';

describe('Component Tests', () => {

    describe('ComerciosMySuffix Management Delete Component', () => {
        let comp: ComerciosMySuffixDeleteDialogComponent;
        let fixture: ComponentFixture<ComerciosMySuffixDeleteDialogComponent>;
        let service: ComerciosMySuffixService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [MVtasTestModule],
                declarations: [ComerciosMySuffixDeleteDialogComponent],
                providers: [
                    ComerciosMySuffixService
                ]
            })
            .overrideTemplate(ComerciosMySuffixDeleteDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(ComerciosMySuffixDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ComerciosMySuffixService);
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
