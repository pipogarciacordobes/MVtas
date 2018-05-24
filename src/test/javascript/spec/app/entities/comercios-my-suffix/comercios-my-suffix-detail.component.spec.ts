/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { MVtasTestModule } from '../../../test.module';
import { ComerciosMySuffixDetailComponent } from '../../../../../../main/webapp/app/entities/comercios-my-suffix/comercios-my-suffix-detail.component';
import { ComerciosMySuffixService } from '../../../../../../main/webapp/app/entities/comercios-my-suffix/comercios-my-suffix.service';
import { ComerciosMySuffix } from '../../../../../../main/webapp/app/entities/comercios-my-suffix/comercios-my-suffix.model';

describe('Component Tests', () => {

    describe('ComerciosMySuffix Management Detail Component', () => {
        let comp: ComerciosMySuffixDetailComponent;
        let fixture: ComponentFixture<ComerciosMySuffixDetailComponent>;
        let service: ComerciosMySuffixService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [MVtasTestModule],
                declarations: [ComerciosMySuffixDetailComponent],
                providers: [
                    ComerciosMySuffixService
                ]
            })
            .overrideTemplate(ComerciosMySuffixDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(ComerciosMySuffixDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ComerciosMySuffixService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new HttpResponse({
                    body: new ComerciosMySuffix(123)
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.comercios).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
