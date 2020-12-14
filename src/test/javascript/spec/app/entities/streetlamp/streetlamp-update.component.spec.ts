import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { LampaderumTestModule } from '../../../test.module';
import { StreetlampUpdateComponent } from 'app/entities/streetlamp/streetlamp-update.component';
import { StreetlampService } from 'app/entities/streetlamp/streetlamp.service';
import { Streetlamp } from 'app/shared/model/streetlamp.model';

describe('Component Tests', () => {
  describe('Streetlamp Management Update Component', () => {
    let comp: StreetlampUpdateComponent;
    let fixture: ComponentFixture<StreetlampUpdateComponent>;
    let service: StreetlampService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [LampaderumTestModule],
        declarations: [StreetlampUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(StreetlampUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(StreetlampUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(StreetlampService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Streetlamp(123);
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
        const entity = new Streetlamp();
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
