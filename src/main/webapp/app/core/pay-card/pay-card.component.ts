import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { FormBuilder } from '@angular/forms';

import { RxwebValidators } from '@rxweb/reactive-form-validators';

@Component({
  selector: 'jhi-pay-card',
  templateUrl: './pay-card.component.html',
  styleUrls: ['./pay-card.component.scss'],
})
export class PayCardComponent implements OnInit {
  userFormGroup: any;

  creditCardTypes = ['Visa', 'AmericanExpress', 'Maestro', 'JCB', 'Discover', 'DinersClub', 'MasterCard'];
  @Output() numberCartEvent = new EventEmitter<number>();

  constructor(private formBuilder: FormBuilder) {}

  ngOnInit(): void {
    this.userFormGroup = this.formBuilder.group({
      cardType: ['Visa'],
      creditCard: ['', RxwebValidators.creditCard({ fieldName: 'cardType' })],
    });
  }

  sendCardNumber(): void {
    this.numberCartEvent.emit(this.userFormGroup.get('creditCard').value);
  }
}
