import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { LampaderumTestModule } from '../../../test.module';
import { RoworderDetailComponent } from 'app/entities/roworder/roworder-detail.component';
import { Roworder } from 'app/shared/model/roworder.model';

describe('Component Tests', () => {
  describe('Roworder Management Detail Component', () => {
    let comp: RoworderDetailComponent;
    let fixture: ComponentFixture<RoworderDetailComponent>;
    const route = ({ data: of({ roworder: new Roworder(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [LampaderumTestModule],
        declarations: [RoworderDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(RoworderDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(RoworderDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load roworder on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.roworder).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
