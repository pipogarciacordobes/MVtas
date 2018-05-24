/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { MVtasTestModule } from '../../../test.module';
import { PVMySuffixComponent } from '../../../../../../main/webapp/app/entities/pv-my-suffix/pv-my-suffix.component';
import { PVMySuffixService } from '../../../../../../main/webapp/app/entities/pv-my-suffix/pv-my-suffix.service';
import { PVMySuffix } from '../../../../../../main/webapp/app/entities/pv-my-suffix/pv-my-suffix.model';

describe('Component Tests', () => {

    describe('PVMySuffix Management Component', () => {
        let comp: PVMySuffixComponent;
        let fixture: ComponentFixture<PVMySuffixComponent>;
        let service: PVMySuffixService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [MVtasTestModule],
                declarations: [PVMySuffixComponent],
                providers: [
                    PVMySuffixService
                ]
            })
            .overrideTemplate(PVMySuffixComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(PVMySuffixComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(PVMySuffixService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new HttpHeaders().append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of(new HttpResponse({
                    body: [new PVMySuffix(123)],
                    headers
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.pVS[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
