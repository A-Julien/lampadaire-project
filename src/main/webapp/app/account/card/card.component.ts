import { Component, Input, OnInit } from '@angular/core';
import { Creditcard } from 'app/shared/model/creditcard.model';

@Component({
  selector: 'jhi-card',
  templateUrl: './card.component.html',
  styleUrls: ['./card.component.scss'],
})
export class CardComponent implements OnInit {
  @Input()
  creditCard: Creditcard | null = null;

  constructor() {}

  ngOnInit(): void {}
}
