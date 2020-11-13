import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { IStreetlamp } from '../../shared/model/streetlamp.model';

@Component({
  selector: 'jhi-detail-page',
  templateUrl: './detail-page.component.html',
  styleUrls: ['../detail-page/detail-page.component.scss']
})
export class DetailPageComponent implements OnInit {
  streetlamp: IStreetlamp | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ streetlamp }) => (this.streetlamp = streetlamp));
  }

  previousState(): void {
    window.history.back();
  }


}
