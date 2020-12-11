import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { LampaderumTestModule } from '../../../test.module';
import { RoworderUpdateComponent } from 'app/entities/roworder/roworder-update.component';
import { RoworderService } from 'app/entities/roworder/roworder.service';
import { Roworder } from 'app/shared/model/roworder.model';

describe('Component Tests', () => {
  describe('Roworder Management Update Component', () => {
    let comp: RoworderUpdateComponent;
    let fixture: ComponentFixture<RoworderUpdateComponent>;
    let service: RoworderService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [LampaderumTestModule],
        declarations: [RoworderUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(RoworderUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(RoworderUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(RoworderService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Roworder(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new Roworder();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
