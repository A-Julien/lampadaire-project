import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import './vendor';
import { LampaderumSharedModule } from 'app/shared/shared.module';
import { LampaderumCoreModule } from 'app/core/core.module';
import { LampaderumAppRoutingModule } from './app-routing.module';
import { LampaderumHomeModule } from './home/home.module';
import { LampaderumEntityModule } from './entities/entity.module';
// jhipster-needle-angular-add-module-import JHipster will add new module here
import { MainComponent } from './layouts/main/main.component';
import { NavbarComponent } from './layouts/navbar/navbar.component';
import { FooterComponent } from './layouts/footer/footer.component';
import { PageRibbonComponent } from './layouts/profiles/page-ribbon.component';
import { ActiveMenuDirective } from './layouts/navbar/active-menu.directive';
import { ErrorComponent } from './layouts/error/error.component';
import { ProductsPageComponent } from './core/products-page/products-page.component';
import { ShoppingCartComponent } from 'app/core/shopping-cart/shopping-cart.component';
import { OrdersComponent } from 'app/core/orders/orders.component';

@NgModule({
  imports: [
    BrowserModule,
    LampaderumSharedModule,
    LampaderumCoreModule,
    LampaderumHomeModule,
    // jhipster-needle-angular-add-module JHipster will add new module here
    LampaderumEntityModule,
    LampaderumAppRoutingModule,
  ],
  declarations: [MainComponent, NavbarComponent, ErrorComponent, PageRibbonComponent, ActiveMenuDirective, FooterComponent],
  bootstrap: [MainComponent],
})
export class LampaderumAppModule {}
