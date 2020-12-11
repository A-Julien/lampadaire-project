import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { LampaderumTestModule } from '../../../test.module';
import { RowcartDetailComponent } from 'app/entities/rowcart/rowcart-detail.component';
import { Rowcart } from 'app/shared/model/rowcart.model';

describe('Component Tests', () => {
  describe('Rowcart Management Detail Component', () => {
    let comp: RowcartDetailComponent;
    let fixture: ComponentFixture<RowcartDetailComponent>;
    const route = ({ data: of({ rowcart: new Rowcart(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [LampaderumTestModule],
        declarations: [RowcartDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(RowcartDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(RowcartDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load rowcart on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.rowcart).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
