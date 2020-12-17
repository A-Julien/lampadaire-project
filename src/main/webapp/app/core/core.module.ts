import { NgModule, LOCALE_ID } from '@angular/core';
import { DatePipe, registerLocaleData } from '@angular/common';
import { HttpClient, HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { BrowserModule, Title } from '@angular/platform-browser';
import { FaIconLibrary } from '@fortawesome/angular-fontawesome';
import { CookieService } from 'ngx-cookie-service';
import { TranslateModule, TranslateLoader, MissingTranslationHandler } from '@ngx-translate/core';
import { NgxWebstorageModule } from 'ngx-webstorage';
import { NgJhipsterModule, translatePartialLoader, missingTranslationHandler, JhiConfigService, JhiLanguageService } from 'ng-jhipster';
import locale from '@angular/common/locales/fr';

import * as moment from 'moment';
import { NgbDateAdapter, NgbDatepickerConfig } from '@ng-bootstrap/ng-bootstrap';
import { NgbDateMomentAdapter } from 'app/shared/util/datepicker-adapter';

import { AuthInterceptor } from 'app/blocks/interceptor/auth.interceptor';
import { AuthExpiredInterceptor } from 'app/blocks/interceptor/auth-expired.interceptor';
import { ErrorHandlerInterceptor } from 'app/blocks/interceptor/errorhandler.interceptor';
import { NotificationInterceptor } from 'app/blocks/interceptor/notification.interceptor';

import { fontAwesomeIcons } from './icons/font-awesome-icons';
import { DetailPageComponent } from './detail-page/detail-page.component';
import { LampaderumSharedModule } from 'app/shared/shared.module';
import { RouterModule, Routes } from '@angular/router';
import { LampaderumHomeModule } from 'app/home/home.module';
import { PayRecapComponent } from './pay-recap/pay-recap.component';
import { PayFormComponent } from 'app/core/pay-form/pay-form.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { PayCardComponent } from './pay-card/pay-card.component';
import { RxReactiveFormsModule } from '@rxweb/reactive-form-validators';
import { AccountModule } from 'app/account/account.module';

@NgModule({
  imports: [
    HttpClientModule,
    NgxWebstorageModule.forRoot({ prefix: 'jhi', separator: '-' }),
    NgJhipsterModule.forRoot({
      // set below to true to make alerts look like toast
      alertAsToast: false,
      alertTimeout: 5000,
      i18nEnabled: true,
      defaultI18nLang: 'fr',
    }),
    TranslateModule.forRoot({
      loader: {
        provide: TranslateLoader,
        useFactory: translatePartialLoader,
        deps: [HttpClient],
      },
      missingTranslationHandler: {
        provide: MissingTranslationHandler,
        useFactory: missingTranslationHandler,
        deps: [JhiConfigService],
      },
    }),
    LampaderumSharedModule,
    LampaderumHomeModule,
    FormsModule,
    ReactiveFormsModule,
    RxReactiveFormsModule,
    RouterModule,
    AccountModule,
    // RouterModule.forChild(CORE_ROUTE)
  ],
  providers: [
    Title,
    CookieService,
    {
      provide: LOCALE_ID,
      useValue: 'fr',
    },
    { provide: NgbDateAdapter, useClass: NgbDateMomentAdapter },
    DatePipe,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthInterceptor,
      multi: true,
    },
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthExpiredInterceptor,
      multi: true,
    },
    {
      provide: HTTP_INTERCEPTORS,
      useClass: ErrorHandlerInterceptor,
      multi: true,
    },
    {
      provide: HTTP_INTERCEPTORS,
      useClass: NotificationInterceptor,
      multi: true,
    },
  ],
  declarations: [DetailPageComponent, PayRecapComponent, PayFormComponent, PayCardComponent],
  exports: [RouterModule],
})
export class LampaderumCoreModule {
  constructor(iconLibrary: FaIconLibrary, dpConfig: NgbDatepickerConfig, languageService: JhiLanguageService) {
    registerLocaleData(locale);
    iconLibrary.addIcons(...fontAwesomeIcons);
    dpConfig.minDate = { year: moment().year() - 100, month: 1, day: 1 };
    languageService.init();
  }
}
