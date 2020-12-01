import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ISOrder } from 'app/shared/model/s-order.model';

@Component({
  selector: 'jhi-s-order-detail',
  templateUrl: './s-order-detail.component.html',
})
export class SOrderDetailComponent implements OnInit {
  sOrder: ISOrder | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ sOrder }) => (this.sOrder = sOrder));
  }

  previousState(): void {
    window.history.back();
  }
}
