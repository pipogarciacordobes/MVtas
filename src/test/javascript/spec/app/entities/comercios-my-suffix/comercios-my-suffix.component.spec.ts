/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { MVtasTestModule } from '../../../test.module';
import { ComerciosMySuffixComponent } from '../../../../../../main/webapp/app/entities/comercios-my-suffix/comercios-my-suffix.component';
import { ComerciosMySuffixService } from '../../../../../../main/webapp/app/entities/comercios-my-suffix/comercios-my-suffix.service';
import { ComerciosMySuffix } from '../../../../../../main/webapp/app/entities/comercios-my-suffix/comercios-my-suffix.model';

describe('Component Tests', () => {

    describe('ComerciosMySuffix Management Component', () => {
        let comp: ComerciosMySuffixComponent;
        let fixture: ComponentFixture<ComerciosMySuffixComponent>;
        let service: ComerciosMySuffixService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [MVtasTestModule],
                declarations: [ComerciosMySuffixComponent],
                providers: [
                    ComerciosMySuffixService
                ]
            })
            .overrideTemplate(ComerciosMySuffixComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(ComerciosMySuffixComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ComerciosMySuffixService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new HttpHeaders().append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of(new HttpResponse({
                    body: [new ComerciosMySuffix(123)],
                    headers
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.comercios[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
