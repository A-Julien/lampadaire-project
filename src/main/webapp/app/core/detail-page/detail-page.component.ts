import { AfterViewInit, Component, ElementRef, HostListener, OnInit, ViewChild } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Streetlamp } from '../../shared/model/streetlamp.model';
import { Cart } from 'app/shared/model/Cart.model';
import { Subscription } from 'rxjs';
import { LampService } from 'app/core/services/lamp-service.service';
@Component({
  selector: 'jhi-detail-page',
  templateUrl: './detail-page.component.html',
  styleUrls: ['./detail-page.component.scss'],
})
export class DetailPageComponent implements OnInit, AfterViewInit {
  streetlamp: Streetlamp | null = null;

  sticky: boolean;
  elementPosition: any;

  @ViewChild('stickyCart')
  cartElement!: ElementRef;

  constructor(protected activatedRoute: ActivatedRoute) {
    this.sticky = false;
  }

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ streetlamp }) => (this.streetlamp = streetlamp));
  }

  ngAfterViewInit(): void {
    this.elementPosition = this.cartElement.nativeElement.offsetTop;
  }

  previousState(): void {
    window.history.back();
  }

  @HostListener('window:scroll', ['$event'])
  handleScroll(): void {
    const windowScroll = window.pageYOffset;
    if (windowScroll >= this.elementPosition) {
      this.sticky = true;
    } else {
      this.sticky = false;
    }
  }
}
