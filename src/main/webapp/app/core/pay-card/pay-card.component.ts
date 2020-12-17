import { Component, OnInit, Output, EventEmitter, ViewChild, ElementRef, HostListener, AfterViewInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup } from '@angular/forms';

import { RxwebValidators } from '@rxweb/reactive-form-validators';

import { Creditcard } from 'app/shared/model/creditcard.model';

@Component({
  selector: 'jhi-pay-card',
  templateUrl: './pay-card.component.html',
  styleUrls: ['./pay-card.component.scss'],
})
export class PayCardComponent implements OnInit, AfterViewInit {
  userFormGroup: any;

  stickyCard: boolean | undefined;
  elementPosition: any;

  @ViewChild('stickyCard')
  cardElement!: ElementRef;

  creditCardTypes = ['Visa', 'AmericanExpress', 'Maestro', 'JCB', 'Discover', 'DinersClub', 'MasterCard'];

  @Output() numberCartEvent = new EventEmitter<number>();

  constructor(private formBuilder: FormBuilder) {}

  ngOnInit(): void {
    this.userFormGroup = this.formBuilder.group({
      cardType: ['Visa'],
      creditCard: ['', RxwebValidators.creditCard({ fieldName: 'cardType' })],
    });
  }

  ngAfterViewInit(): void {
    this.elementPosition = this.cardElement.nativeElement.offsetTop;
  }

  @HostListener('window:scroll', ['$event'])
  handleScroll(): void {
    const windowScroll = window.pageYOffset;
    if (windowScroll >= this.elementPosition) {
      this.stickyCard = true;
    } else {
      this.stickyCard = false;
    }
  }

  receiveSelectedCard($event: Creditcard): void {
    this.userFormGroup.patchValue({
      creditCard: $event.numcarte,
    });
  }

  sendCardNumber(): void {
    this.numberCartEvent.emit(this.userFormGroup.get('creditCard').value);
  }
}
