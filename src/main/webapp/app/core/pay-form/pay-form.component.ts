import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

@Component({
  selector: 'jhi-pay-form',
  templateUrl: './pay-form.component.html',
  styleUrls: ['./pay-form.component.scss'],
})
export class PayFormComponent implements OnInit {
  payFrom: any;

  constructor() {}

  ngOnInit(): void {
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
}
