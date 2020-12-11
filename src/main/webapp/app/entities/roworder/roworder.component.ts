import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiParseLinks } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IRoworder } from 'app/shared/model/roworder.model';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { RoworderService } from './roworder.service';
import { RoworderDeleteDialogComponent } from './roworder-delete-dialog.component';

@Component({
  selector: 'jhi-roworder',
  templateUrl: './roworder.component.html',
})
export class RoworderComponent implements OnInit, OnDestroy {
  roworders: IRoworder[];
  eventSubscriber?: Subscription;
  itemsPerPage: number;
  links: any;
  page: number;
  predicate: string;
  ascending: boolean;

  constructor(
    protected roworderService: RoworderService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal,
    protected parseLinks: JhiParseLinks
  ) {
    this.roworders = [];
    this.itemsPerPage = ITEMS_PER_PAGE;
    this.page = 0;
    this.links = {
      last: 0,
    };
    this.predicate = 'id';
    this.ascending = true;
  }

  loadAll(): void {
    this.roworderService
      .query({
        page: this.page,
        size: this.itemsPerPage,
        sort: this.sort(),
      })
      .subscribe((res: HttpResponse<IRoworder[]>) => this.paginateRoworders(res.body, res.headers));
  }

  reset(): void {
    this.page = 0;
    this.roworders = [];
    this.loadAll();
  }

  loadPage(page: number): void {
    this.page = page;
    this.loadAll();
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInRoworders();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IRoworder): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInRoworders(): void {
    this.eventSubscriber = this.eventManager.subscribe('roworderListModification', () => this.reset());
  }

  delete(roworder: IRoworder): void {
    const modalRef = this.modalService.open(RoworderDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.roworder = roworder;
  }

  sort(): string[] {
    const result = [this.predicate + ',' + (this.ascending ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected paginateRoworders(data: IRoworder[] | null, headers: HttpHeaders): void {
    const headersLink = headers.get('link');
    this.links = this.parseLinks.parse(headersLink ? headersLink : '');
    if (data) {
      for (let i = 0; i < data.length; i++) {
        this.roworders.push(data[i]);
      }
    }
  }
}
