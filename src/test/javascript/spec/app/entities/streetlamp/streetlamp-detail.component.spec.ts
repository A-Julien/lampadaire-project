import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { LampaderumTestModule } from '../../../test.module';
import { StreetlampDetailComponent } from 'app/entities/streetlamp/streetlamp-detail.component';
import { Streetlamp } from 'app/shared/model/streetlamp.model';

describe('Component Tests', () => {
  describe('Streetlamp Management Detail Component', () => {
    let comp: StreetlampDetailComponent;
    let fixture: ComponentFixture<StreetlampDetailComponent>;
    const route = ({ data: of({ streetlamp: new Streetlamp(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [LampaderumTestModule],
        declarations: [StreetlampDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(StreetlampDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(StreetlampDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load streetlamp on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.streetlamp).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
