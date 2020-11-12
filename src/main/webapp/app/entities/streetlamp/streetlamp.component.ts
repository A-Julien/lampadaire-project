import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiParseLinks } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IStreetlamp } from 'app/shared/model/streetlamp.model';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { StreetlampService } from './streetlamp.service';
import { StreetlampDeleteDialogComponent } from './streetlamp-delete-dialog.component';

@Component({
  selector: 'jhi-streetlamp',
  templateUrl: './streetlamp.component.html',
})
export class StreetlampComponent implements OnInit, OnDestroy {
  streetlamps: IStreetlamp[];
  eventSubscriber?: Subscription;
  itemsPerPage: number;
  links: any;
  page: number;
  predicate: string;
  ascending: boolean;

  constructor(
    protected streetlampService: StreetlampService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal,
    protected parseLinks: JhiParseLinks
  ) {
    this.streetlamps = [];
    this.itemsPerPage = ITEMS_PER_PAGE;
    this.page = 0;
    this.links = {
      last: 0,
    };
    this.predicate = 'id';
    this.ascending = true;
  }

  loadAll(): void {
    this.streetlampService
      .query({
        page: this.page,
        size: this.itemsPerPage,
        sort: this.sort(),
      })
      .subscribe((res: HttpResponse<IStreetlamp[]>) => this.paginateStreetlamps(res.body, res.headers));
  }

  reset(): void {
    this.page = 0;
    this.streetlamps = [];
    this.loadAll();
  }

  loadPage(page: number): void {
    this.page = page;
    this.loadAll();
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInStreetlamps();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IStreetlamp): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInStreetlamps(): void {
    this.eventSubscriber = this.eventManager.subscribe('streetlampListModification', () => this.reset());
  }

  delete(streetlamp: IStreetlamp): void {
    const modalRef = this.modalService.open(StreetlampDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.streetlamp = streetlamp;
  }

  sort(): string[] {
    const result = [this.predicate + ',' + (this.ascending ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected paginateStreetlamps(data: IStreetlamp[] | null, headers: HttpHeaders): void {
    const headersLink = headers.get('link');
    this.links = this.parseLinks.parse(headersLink ? headersLink : '');
    if (data) {
      for (let i = 0; i < data.length; i++) {
        this.streetlamps.push(data[i]);
      }
    }
  }
}
