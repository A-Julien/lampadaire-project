import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiParseLinks } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ICreditcard } from 'app/shared/model/creditcard.model';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { CreditcardService } from './creditcard.service';
import { CreditcardDeleteDialogComponent } from './creditcard-delete-dialog.component';

@Component({
  selector: 'jhi-creditcard',
  templateUrl: './creditcard.component.html',
})
export class CreditcardComponent implements OnInit, OnDestroy {
  creditcards: ICreditcard[];
  eventSubscriber?: Subscription;
  itemsPerPage: number;
  links: any;
  page: number;
  predicate: string;
  ascending: boolean;

  constructor(
    protected creditcardService: CreditcardService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal,
    protected parseLinks: JhiParseLinks
  ) {
    this.creditcards = [];
    this.itemsPerPage = ITEMS_PER_PAGE;
    this.page = 0;
    this.links = {
      last: 0,
    };
    this.predicate = 'id';
    this.ascending = true;
  }

  loadAll(): void {
    this.creditcardService
      .query({
        page: this.page,
        size: this.itemsPerPage,
        sort: this.sort(),
      })
      .subscribe((res: HttpResponse<ICreditcard[]>) => this.paginateCreditcards(res.body, res.headers));
  }

  reset(): void {
    this.page = 0;
    this.creditcards = [];
    this.loadAll();
  }

  loadPage(page: number): void {
    this.page = page;
    this.loadAll();
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInCreditcards();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: ICreditcard): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInCreditcards(): void {
    this.eventSubscriber = this.eventManager.subscribe('creditcardListModification', () => this.reset());
  }

  delete(creditcard: ICreditcard): void {
    const modalRef = this.modalService.open(CreditcardDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.creditcard = creditcard;
  }

  sort(): string[] {
    const result = [this.predicate + ',' + (this.ascending ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected paginateCreditcards(data: ICreditcard[] | null, headers: HttpHeaders): void {
    const headersLink = headers.get('link');
    this.links = this.parseLinks.parse(headersLink ? headersLink : '');
    if (data) {
      for (let i = 0; i < data.length; i++) {
        this.creditcards.push(data[i]);
      }
    }
  }
}
