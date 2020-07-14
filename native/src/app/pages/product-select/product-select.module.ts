import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { ProductSelectPageRoutingModule } from './product-select-routing.module';

import { ProductSelectPage } from './product-select.page';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    ProductSelectPageRoutingModule
  ],
  declarations: [ProductSelectPage]
})
export class ProductSelectPageModule {}
