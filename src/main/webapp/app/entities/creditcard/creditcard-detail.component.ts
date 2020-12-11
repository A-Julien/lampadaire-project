import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ICreditcard } from 'app/shared/model/creditcard.model';

@Component({
  selector: 'jhi-creditcard-detail',
  templateUrl: './creditcard-detail.component.html',
})
export class CreditcardDetailComponent implements OnInit {
  creditcard: ICreditcard | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ creditcard }) => (this.creditcard = creditcard));
  }

  previousState(): void {
    window.history.back();
  }
}
