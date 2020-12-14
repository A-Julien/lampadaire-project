import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ICartpersi } from 'app/shared/model/cartpersi.model';

@Component({
  selector: 'jhi-cartpersi-detail',
  templateUrl: './cartpersi-detail.component.html',
})
export class CartpersiDetailComponent implements OnInit {
  cartpersi: ICartpersi | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ cartpersi }) => (this.cartpersi = cartpersi));
  }

  previousState(): void {
    window.history.back();
  }
}
