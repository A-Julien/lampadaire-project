import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiParseLinks } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IRowcart } from 'app/shared/model/rowcart.model';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { RowcartService } from './rowcart.service';
import { RowcartDeleteDialogComponent } from './rowcart-delete-dialog.component';

@Component({
  selector: 'jhi-rowcart',
  templateUrl: './rowcart.component.html',
})
export class RowcartComponent implements OnInit, OnDestroy {
  rowcarts: IRowcart[];
  eventSubscriber?: Subscription;
  itemsPerPage: number;
  links: any;
  page: number;
  predicate: string;
  ascending: boolean;

  constructor(
    protected rowcartService: RowcartService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal,
    protected parseLinks: JhiParseLinks
  ) {
    this.rowcarts = [];
    this.itemsPerPage = ITEMS_PER_PAGE;
    this.page = 0;
    this.links = {
      last: 0,
    };
    this.predicate = 'id';
    this.ascending = true;
  }

  loadAll(): void {
    this.rowcartService
      .query({
        page: this.page,
        size: this.itemsPerPage,
        sort: this.sort(),
      })
      .subscribe((res: HttpResponse<IRowcart[]>) => this.paginateRowcarts(res.body, res.headers));
  }

  reset(): void {
    this.page = 0;
    this.rowcarts = [];
    this.loadAll();
  }

  loadPage(page: number): void {
    this.page = page;
    this.loadAll();
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInRowcarts();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IRowcart): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInRowcarts(): void {
    this.eventSubscriber = this.eventManager.subscribe('rowcartListModification', () => this.reset());
  }

  delete(rowcart: IRowcart): void {
    const modalRef = this.modalService.open(RowcartDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.rowcart = rowcart;
  }

  sort(): string[] {
    const result = [this.predicate + ',' + (this.ascending ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected paginateRowcarts(data: IRowcart[] | null, headers: HttpHeaders): void {
    const headersLink = headers.get('link');
    this.links = this.parseLinks.parse(headersLink ? headersLink : '');
    if (data) {
      for (let i = 0; i < data.length; i++) {
        this.rowcarts.push(data[i]);
      }
    }
  }
}
