/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { MVtasTestModule } from '../../../test.module';
import { TrxsMySuffixDetailComponent } from '../../../../../../main/webapp/app/entities/trxs-my-suffix/trxs-my-suffix-detail.component';
import { TrxsMySuffixService } from '../../../../../../main/webapp/app/entities/trxs-my-suffix/trxs-my-suffix.service';
import { TrxsMySuffix } from '../../../../../../main/webapp/app/entities/trxs-my-suffix/trxs-my-suffix.model';

describe('Component Tests', () => {

    describe('TrxsMySuffix Management Detail Component', () => {
        let comp: TrxsMySuffixDetailComponent;
        let fixture: ComponentFixture<TrxsMySuffixDetailComponent>;
        let service: TrxsMySuffixService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [MVtasTestModule],
                declarations: [TrxsMySuffixDetailComponent],
                providers: [
                    TrxsMySuffixService
                ]
            })
            .overrideTemplate(TrxsMySuffixDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(TrxsMySuffixDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(TrxsMySuffixService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new HttpResponse({
                    body: new TrxsMySuffix(123)
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.trxs).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
