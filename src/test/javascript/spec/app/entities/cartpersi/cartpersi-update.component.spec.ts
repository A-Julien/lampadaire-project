import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { LampaderumTestModule } from '../../../test.module';
import { CartpersiUpdateComponent } from 'app/entities/cartpersi/cartpersi-update.component';
import { CartpersiService } from 'app/entities/cartpersi/cartpersi.service';
import { Cartpersi } from 'app/shared/model/cartpersi.model';

describe('Component Tests', () => {
  describe('Cartpersi Management Update Component', () => {
    let comp: CartpersiUpdateComponent;
    let fixture: ComponentFixture<CartpersiUpdateComponent>;
    let service: CartpersiService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [LampaderumTestModule],
        declarations: [CartpersiUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(CartpersiUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(CartpersiUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(CartpersiService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Cartpersi(123);
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
        const entity = new Cartpersi();
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
