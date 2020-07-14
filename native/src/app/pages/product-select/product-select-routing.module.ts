import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { ProductSelectPage } from './product-select.page';

const routes: Routes = [
  {
    path: '',
    component: ProductSelectPage
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class ProductSelectPageRoutingModule {}
