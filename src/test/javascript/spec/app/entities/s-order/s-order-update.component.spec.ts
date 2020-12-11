import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { LampaderumTestModule } from '../../../test.module';
import { SOrderUpdateComponent } from 'app/entities/s-order/s-order-update.component';
import { SOrderService } from 'app/entities/s-order/s-order.service';
import { SOrder } from 'app/shared/model/s-order.model';

describe('Component Tests', () => {
  describe('SOrder Management Update Component', () => {
    let comp: SOrderUpdateComponent;
    let fixture: ComponentFixture<SOrderUpdateComponent>;
    let service: SOrderService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [LampaderumTestModule],
        declarations: [SOrderUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(SOrderUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(SOrderUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(SOrderService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new SOrder(123);
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
        const entity = new SOrder();
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
