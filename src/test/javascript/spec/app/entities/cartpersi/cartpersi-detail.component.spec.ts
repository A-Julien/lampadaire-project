import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { LampaderumTestModule } from '../../../test.module';
import { CartpersiDetailComponent } from 'app/entities/cartpersi/cartpersi-detail.component';
import { Cartpersi } from 'app/shared/model/cartpersi.model';

describe('Component Tests', () => {
  describe('Cartpersi Management Detail Component', () => {
    let comp: CartpersiDetailComponent;
    let fixture: ComponentFixture<CartpersiDetailComponent>;
    const route = ({ data: of({ cartpersi: new Cartpersi(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [LampaderumTestModule],
        declarations: [CartpersiDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(CartpersiDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(CartpersiDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load cartpersi on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.cartpersi).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
