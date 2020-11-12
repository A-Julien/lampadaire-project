import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IStreetlamp } from 'app/shared/model/streetlamp.model';

@Component({
  selector: 'jhi-streetlamp-detail',
  templateUrl: './streetlamp-detail.component.html',
})
export class StreetlampDetailComponent implements OnInit {
  streetlamp: IStreetlamp | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ streetlamp }) => (this.streetlamp = streetlamp));
  }

  previousState(): void {
    window.history.back();
  }
}
