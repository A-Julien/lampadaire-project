import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { LampaderumTestModule } from '../../../test.module';
import { CreditcardUpdateComponent } from 'app/entities/creditcard/creditcard-update.component';
import { CreditcardService } from 'app/entities/creditcard/creditcard.service';
import { Creditcard } from 'app/shared/model/creditcard.model';

describe('Component Tests', () => {
  describe('Creditcard Management Update Component', () => {
    let comp: CreditcardUpdateComponent;
    let fixture: ComponentFixture<CreditcardUpdateComponent>;
    let service: CreditcardService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [LampaderumTestModule],
        declarations: [CreditcardUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(CreditcardUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(CreditcardUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(CreditcardService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Creditcard(123);
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
        const entity = new Creditcard();
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
