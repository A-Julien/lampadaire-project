import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { LampaderumTestModule } from '../../../test.module';
import { SOrderDetailComponent } from 'app/entities/s-order/s-order-detail.component';
import { SOrder } from 'app/shared/model/s-order.model';

describe('Component Tests', () => {
  describe('SOrder Management Detail Component', () => {
    let comp: SOrderDetailComponent;
    let fixture: ComponentFixture<SOrderDetailComponent>;
    const route = ({ data: of({ sOrder: new SOrder(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [LampaderumTestModule],
        declarations: [SOrderDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(SOrderDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(SOrderDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load sOrder on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.sOrder).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
