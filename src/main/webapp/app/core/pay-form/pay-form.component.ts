import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators, FormBuilder } from '@angular/forms';
import { ProductOrder } from 'app/shared/model/OrderProduct.model';
import { LampService } from 'app/core/services/lamp-service.service';

@Component({
  selector: 'jhi-pay-form',
  templateUrl: './pay-form.component.html',
  styleUrls: ['./pay-form.component.scss'],
})
export class PayFormComponent implements OnInit {
  submitted: boolean | undefined;
  payFrom: any;
  productOrders: ProductOrder[] = [];
  constructor(private lampService: LampService, private _fb: FormBuilder) {}

  ngOnInit(): void {
    this.submitted = false;
    this.productOrders = [];
    this.loadProducts();

    this.payFrom = new FormGroup({
      cmdName: new FormControl(null, [Validators.required]),
      cmdCountry: new FormControl('France', [Validators.required]),
      cmdCity: new FormControl(null, [Validators.required]),
      cmdAddr: new FormControl(null, [Validators.required]),
      cmdLName: new FormControl(null, [Validators.required]),
    });
  }

  onSubmit(): void {
    //console.log(this.signupForm);
    /* eslint no-console: ["error", { allow: ["warn", "error"] }] */
    alert(this.payFrom.get('cmdName').value);
  }

  loadProducts(): void {
    this.productOrders = this.lampService.ProductOrders.productOrders;
  }
}
