import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IRowcart } from 'app/shared/model/rowcart.model';

@Component({
  selector: 'jhi-rowcart-detail',
  templateUrl: './rowcart-detail.component.html',
})
export class RowcartDetailComponent implements OnInit {
  rowcart: IRowcart | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ rowcart }) => (this.rowcart = rowcart));
  }

  previousState(): void {
    window.history.back();
  }
}
