/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { MVtasTestModule } from '../../../test.module';
import { TrxsMySuffixComponent } from '../../../../../../main/webapp/app/entities/trxs-my-suffix/trxs-my-suffix.component';
import { TrxsMySuffixService } from '../../../../../../main/webapp/app/entities/trxs-my-suffix/trxs-my-suffix.service';
import { TrxsMySuffix } from '../../../../../../main/webapp/app/entities/trxs-my-suffix/trxs-my-suffix.model';

describe('Component Tests', () => {

    describe('TrxsMySuffix Management Component', () => {
        let comp: TrxsMySuffixComponent;
        let fixture: ComponentFixture<TrxsMySuffixComponent>;
        let service: TrxsMySuffixService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [MVtasTestModule],
                declarations: [TrxsMySuffixComponent],
                providers: [
                    TrxsMySuffixService
                ]
            })
            .overrideTemplate(TrxsMySuffixComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(TrxsMySuffixComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(TrxsMySuffixService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new HttpHeaders().append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of(new HttpResponse({
                    body: [new TrxsMySuffix(123)],
                    headers
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.trxs[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
