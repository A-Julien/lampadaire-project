import { AfterViewInit, Component, ElementRef, HostListener, OnInit, ViewChild } from '@angular/core';
import { FormGroup, FormControl, Validators, FormBuilder } from '@angular/forms';
import { ProductOrder } from 'app/shared/model/OrderProduct.model';
import { LampService } from 'app/core/services/lamp-service.service';
import { ISOrder, SOrder } from 'app/shared/model/s-order.model';
import { Roworder } from 'app/shared/model/roworder.model';
import { RoworderService } from 'app/entities/roworder/roworder.service';
import { SOrderService } from 'app/entities/s-order/s-order.service';
import { Account } from 'app/core/user/account.model';
import { AccountService } from 'app/core/auth/account.service';
import { UserService } from 'app/core/user/user.service';
import { User } from 'app/core/user/user.model';
import { ApplicationUserService } from 'app/entities/application-user/application-user.service';
import { ApplicationUser } from 'app/shared/model/application-user.model';
import { Creditcard } from 'app/shared/model/creditcard.model';
import { NgbDateMomentAdapter } from 'app/shared/util/datepicker-adapter';
import * as moment from 'moment';

@Component({
  selector: 'jhi-pay-form',
  templateUrl: './pay-form.component.html',
  styleUrls: ['./pay-form.component.scss'],
})
export class PayFormComponent implements OnInit, AfterViewInit {
  submitted: boolean | undefined;
  payFrom: any;
  productOrders: ProductOrder[] = [];
  payNow: boolean | undefined;
  totalCmd: number | undefined;

  stickyForm: boolean | undefined;
  elementPosition: any;
  numberCard: number | undefined;

  Account: Account | null = null;
  User: User | null = null;
  applicationUser: ApplicationUser | null = null;

  @ViewChild('stickyForm')
  cartElement!: ElementRef;

  constructor(
    private lampService: LampService,
    private _fb: FormBuilder,
    private roworderService: RoworderService,
    private sOrderService: SOrderService,
    private accountService: AccountService,
    private us: UserService,
    private ap: ApplicationUserService
  ) {}

  ngAfterViewInit(): void {
    this.elementPosition = this.cartElement.nativeElement.offsetTop;
  }

  ngOnInit(): void {
    this.stickyForm = false;
    this.payNow = false;
    this.submitted = false;
    this.productOrders = [];
    this.loadProducts();
    this.totalCmd = this.lampService.Total;

    if (Account) {
      this.accountService.identity().subscribe(account => {
        this.Account = account;
      });

      this.us.find(this.Account!.login).subscribe(user => {
        this.User = user;

        this.ap.findbyuserid(this.User.id).subscribe((body: any) => {
          this.applicationUser = new ApplicationUser(
            body.body.id,
            body.body.siret,
            body.body.userLogin,
            body.body.userId,
            new SOrder()[0],
            new Creditcard()[0],
            body.body.cartpersiId
          );
          //this.sOrderService.create(
        });
      });
    }

    this.payFrom = new FormGroup({
      cmdName: new FormControl(null, [Validators.required]),
      cmdCountry: new FormControl('France', [Validators.required]),
      cmdCity: new FormControl(null, [Validators.required]),
      cmdAddr: new FormControl(null, [Validators.required]),
      cmdLName: new FormControl(null, [Validators.required]),
    });
  }

  @HostListener('window:scroll', ['$event'])
  handleScroll(): void {
    const windowScroll = window.pageYOffset;
    if (windowScroll >= this.elementPosition) {
      this.stickyForm = true;
    } else {
      this.stickyForm = false;
    }
  }

  private createSorder(): ISOrder {
    return {
      ...new SOrder(),
      id: undefined,
      datecommande: moment(),
      applicationUserId: this.applicationUser!.id,
    };
  }

  receivePayment($event: any): void {
    this.numberCard = $event;

    this.sOrderService.create(this.createSorder()).subscribe((body: any) => {
      for (let i = 0; i < this.productOrders.length; i++) {
        this.roworderService
          .create(
            new Roworder(
              undefined,
              12, //this.productOrders[i].product.pricestreetlamp,
              this.productOrders[i].quantity,
              this.productOrders[i].product.id,
              body.body.id
            )
          )
          .subscribe(() => {});
      }
    });
  }

  onSubmit(): void {
    //console.log(this.signupForm);
    /* eslint no-console: ["error", { allow: ["warn", "error"] }] */
    this.payNow = true;
  }

  loadProducts(): void {
    this.productOrders = this.lampService.getCart(); //this.lampService.ProductOrders.productOrders;
  }

  returnToForm(): void {
    this.payNow = false;
  }
}
