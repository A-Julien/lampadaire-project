import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiParseLinks } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IApplicationUser } from 'app/shared/model/application-user.model';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { ApplicationUserService } from './application-user.service';
import { ApplicationUserDeleteDialogComponent } from './application-user-delete-dialog.component';

@Component({
  selector: 'jhi-application-user',
  templateUrl: './application-user.component.html',
})
export class ApplicationUserComponent implements OnInit, OnDestroy {
  applicationUsers: IApplicationUser[];
  eventSubscriber?: Subscription;
  itemsPerPage: number;
  links: any;
  page: number;
  predicate: string;
  ascending: boolean;

  constructor(
    protected applicationUserService: ApplicationUserService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal,
    protected parseLinks: JhiParseLinks
  ) {
    this.applicationUsers = [];
    this.itemsPerPage = ITEMS_PER_PAGE;
    this.page = 0;
    this.links = {
      last: 0,
    };
    this.predicate = 'id';
    this.ascending = true;
  }

  loadAll(): void {
    this.applicationUserService
      .query({
        page: this.page,
        size: this.itemsPerPage,
        sort: this.sort(),
      })
      .subscribe((res: HttpResponse<IApplicationUser[]>) => this.paginateApplicationUsers(res.body, res.headers));
  }

  reset(): void {
    this.page = 0;
    this.applicationUsers = [];
    this.loadAll();
  }

  loadPage(page: number): void {
    this.page = page;
    this.loadAll();
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInApplicationUsers();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IApplicationUser): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInApplicationUsers(): void {
    this.eventSubscriber = this.eventManager.subscribe('applicationUserListModification', () => this.reset());
  }

  delete(applicationUser: IApplicationUser): void {
    const modalRef = this.modalService.open(ApplicationUserDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.applicationUser = applicationUser;
  }

  sort(): string[] {
    const result = [this.predicate + ',' + (this.ascending ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected paginateApplicationUsers(data: IApplicationUser[] | null, headers: HttpHeaders): void {
    const headersLink = headers.get('link');
    this.links = this.parseLinks.parse(headersLink ? headersLink : '');
    if (data) {
      for (let i = 0; i < data.length; i++) {
        this.applicationUsers.push(data[i]);
      }
    }
  }
}
