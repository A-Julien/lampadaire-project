import { Component, OnInit, ViewChild } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Streetlamp } from '../../shared/model/streetlamp.model';
import { ShoppingCartComponent } from 'app/core/shopping-cart/shopping-cart.component';

@Component({
  selector: 'jhi-detail-page',
  templateUrl: './detail-page.component.html',
  styleUrls: ['./detail-page.component.scss']
})
export class DetailPageComponent implements OnInit {
  streetlamp: Streetlamp | null = null;

  @ViewChild('shoppingCartC')
  shoppingCartC!: ShoppingCartComponent;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ streetlamp }) => (this.streetlamp = streetlamp));
  }

  previousState(): void {
    window.history.back();
  }


}
