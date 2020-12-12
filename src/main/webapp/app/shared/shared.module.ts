import { NgModule } from '@angular/core';
import { LampaderumSharedLibsModule } from './shared-libs.module';
import { FindLanguageFromKeyPipe } from './language/find-language-from-key.pipe';
import { AlertComponent } from './alert/alert.component';
import { AlertErrorComponent } from './alert/alert-error.component';
import { LoginModalComponent } from './login/login.component';
import { HasAnyAuthorityDirective } from './auth/has-any-authority.directive';
import { PayFormComponent } from 'app/core/pay-form/pay-form.component';

@NgModule({
  imports: [LampaderumSharedLibsModule],
  declarations: [
    FindLanguageFromKeyPipe,
    AlertComponent,
    AlertErrorComponent,
    LoginModalComponent,
    HasAnyAuthorityDirective,
    PayFormComponent,
  ],
  entryComponents: [LoginModalComponent],
  exports: [
    LampaderumSharedLibsModule,
    FindLanguageFromKeyPipe,
    AlertComponent,
    AlertErrorComponent,
    LoginModalComponent,
    HasAnyAuthorityDirective,
    PayFormComponent,
  ],
})
export class LampaderumSharedModule {}
