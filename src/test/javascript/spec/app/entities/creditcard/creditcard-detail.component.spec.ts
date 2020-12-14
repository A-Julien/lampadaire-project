import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { LampaderumTestModule } from '../../../test.module';
import { CreditcardDetailComponent } from 'app/entities/creditcard/creditcard-detail.component';
import { Creditcard } from 'app/shared/model/creditcard.model';

describe('Component Tests', () => {
  describe('Creditcard Management Detail Component', () => {
    let comp: CreditcardDetailComponent;
    let fixture: ComponentFixture<CreditcardDetailComponent>;
    const route = ({ data: of({ creditcard: new Creditcard(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [LampaderumTestModule],
        declarations: [CreditcardDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(CreditcardDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(CreditcardDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load creditcard on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.creditcard).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
