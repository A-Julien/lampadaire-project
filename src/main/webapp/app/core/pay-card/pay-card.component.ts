import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';

import { RxwebValidators } from '@rxweb/reactive-form-validators';

@Component({
  selector: 'jhi-pay-card',
  templateUrl: './pay-card.component.html',
  styleUrls: ['./pay-card.component.scss'],
})
export class PayCardComponent implements OnInit {
  userFormGroup: any;

  creditCardTypes = ['Visa', 'AmericanExpress', 'Maestro', 'JCB', 'Discover', 'DinersClub', 'MasterCard'];
  constructor(private formBuilder: FormBuilder) {}

  ngOnInit(): void {
    this.userFormGroup = this.formBuilder.group({
      cardType: ['Visa'],
      creditCard: ['', RxwebValidators.creditCard({ fieldName: 'cardType' })],
    });
  }
}
