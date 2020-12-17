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
import { pdfMake } from 'pdfmake/build/vfs_fonts';
import { PdfServiceService } from 'app/core/services/pdf-service.service';
import { Cart } from 'app/shared/model/Cart.model';
import { Subscription } from 'rxjs';

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

  cart!: Cart;
  sub!: Subscription;

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
    private ap: ApplicationUserService,
    private pdfservice: PdfServiceService
  ) {}

  ngAfterViewInit(): void {
    this.elementPosition = this.cartElement.nativeElement.offsetTop;
  }

  ngOnInit(): void {
    this.stickyForm = false;
    this.payNow = false;
    this.submitted = false;
    this.productOrders = [];
    this.subToCart();
    this.cart = this.lampService.get();
    this.totalCmd = this.lampService.calculateTotal(this.cart.lamps);

    if (Account) {
      this.accountService.identity().subscribe(account => {
        this.Account = account;

        this.us.find(this.Account!.login).subscribe(user => {
          this.User = user;
          alert(user.id);
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

  private subToCart(): void {
    //this.productOrders = this.lampService.ProductOrders.productOrders;
    //this.productOrders = this.lampService.getCart();
    this.sub = this.lampService.ProductOrderChanged.subscribe(() => {
      this.cart = this.lampService.get();
      this.totalCmd = this.lampService.calculateTotal(this.cart.lamps);
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
    this.createBill();

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
    /* eslint no-console: ["error", { allow: ["warn", "error"] }] */
    this.payNow = true;
  }

  returnToForm(): void {
    this.payNow = false;
  }

  createBill(): void {
    this.pdfservice.addProduct(this.lampService.get().lamps);
    this.pdfservice.generatePDF();
  }
}
