import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { LampaderumTestModule } from '../../../test.module';
import { RowcartUpdateComponent } from 'app/entities/rowcart/rowcart-update.component';
import { RowcartService } from 'app/entities/rowcart/rowcart.service';
import { Rowcart } from 'app/shared/model/rowcart.model';

describe('Component Tests', () => {
  describe('Rowcart Management Update Component', () => {
    let comp: RowcartUpdateComponent;
    let fixture: ComponentFixture<RowcartUpdateComponent>;
    let service: RowcartService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [LampaderumTestModule],
        declarations: [RowcartUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(RowcartUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(RowcartUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(RowcartService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Rowcart(123);
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
        const entity = new Rowcart();
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
