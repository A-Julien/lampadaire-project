import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiParseLinks } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ICartpersi } from 'app/shared/model/cartpersi.model';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { CartpersiService } from './cartpersi.service';
import { CartpersiDeleteDialogComponent } from './cartpersi-delete-dialog.component';

@Component({
  selector: 'jhi-cartpersi',
  templateUrl: './cartpersi.component.html',
})
export class CartpersiComponent implements OnInit, OnDestroy {
  cartpersis: ICartpersi[];
  eventSubscriber?: Subscription;
  itemsPerPage: number;
  links: any;
  page: number;
  predicate: string;
  ascending: boolean;

  constructor(
    protected cartpersiService: CartpersiService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal,
    protected parseLinks: JhiParseLinks
  ) {
    this.cartpersis = [];
    this.itemsPerPage = ITEMS_PER_PAGE;
    this.page = 0;
    this.links = {
      last: 0,
    };
    this.predicate = 'id';
    this.ascending = true;
  }

  loadAll(): void {
    this.cartpersiService
      .query({
        page: this.page,
        size: this.itemsPerPage,
        sort: this.sort(),
      })
      .subscribe((res: HttpResponse<ICartpersi[]>) => this.paginateCartpersis(res.body, res.headers));
  }

  reset(): void {
    this.page = 0;
    this.cartpersis = [];
    this.loadAll();
  }

  loadPage(page: number): void {
    this.page = page;
    this.loadAll();
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInCartpersis();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: ICartpersi): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInCartpersis(): void {
    this.eventSubscriber = this.eventManager.subscribe('cartpersiListModification', () => this.reset());
  }

  delete(cartpersi: ICartpersi): void {
    const modalRef = this.modalService.open(CartpersiDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.cartpersi = cartpersi;
  }

  sort(): string[] {
    const result = [this.predicate + ',' + (this.ascending ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected paginateCartpersis(data: ICartpersi[] | null, headers: HttpHeaders): void {
    const headersLink = headers.get('link');
    this.links = this.parseLinks.parse(headersLink ? headersLink : '');
    if (data) {
      for (let i = 0; i < data.length; i++) {
        this.cartpersis.push(data[i]);
      }
    }
  }
}
