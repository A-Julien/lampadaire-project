import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IRoworder } from 'app/shared/model/roworder.model';

@Component({
  selector: 'jhi-roworder-detail',
  templateUrl: './roworder-detail.component.html',
})
export class RoworderDetailComponent implements OnInit {
  roworder: IRoworder | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ roworder }) => (this.roworder = roworder));
  }

  previousState(): void {
    window.history.back();
  }
}
