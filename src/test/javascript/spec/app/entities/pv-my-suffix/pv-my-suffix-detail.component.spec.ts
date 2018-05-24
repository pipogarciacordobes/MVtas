/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { MVtasTestModule } from '../../../test.module';
import { PVMySuffixDetailComponent } from '../../../../../../main/webapp/app/entities/pv-my-suffix/pv-my-suffix-detail.component';
import { PVMySuffixService } from '../../../../../../main/webapp/app/entities/pv-my-suffix/pv-my-suffix.service';
import { PVMySuffix } from '../../../../../../main/webapp/app/entities/pv-my-suffix/pv-my-suffix.model';

describe('Component Tests', () => {

    describe('PVMySuffix Management Detail Component', () => {
        let comp: PVMySuffixDetailComponent;
        let fixture: ComponentFixture<PVMySuffixDetailComponent>;
        let service: PVMySuffixService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [MVtasTestModule],
                declarations: [PVMySuffixDetailComponent],
                providers: [
                    PVMySuffixService
                ]
            })
            .overrideTemplate(PVMySuffixDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(PVMySuffixDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(PVMySuffixService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new HttpResponse({
                    body: new PVMySuffix(123)
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.pV).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
