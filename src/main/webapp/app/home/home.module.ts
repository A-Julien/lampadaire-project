import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { LampaderumSharedModule } from 'app/shared/shared.module';
import { HOME_ROUTE } from './home.route';
import { HomeComponent } from './home.component';
import { ProductsPageComponent } from 'app/core/products-page/products-page.component';
import { ShoppingCartComponent } from 'app/core/shopping-cart/shopping-cart.component';
import { OrdersComponent } from 'app/core/orders/orders.component';

@NgModule({
  imports: [LampaderumSharedModule, RouterModule.forChild([HOME_ROUTE])],
  declarations: [HomeComponent, ProductsPageComponent, ShoppingCartComponent, OrdersComponent],
})
export class LampaderumHomeModule {}
