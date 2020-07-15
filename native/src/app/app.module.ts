import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { RouteReuseStrategy } from '@angular/router';

import { IonicModule, IonicRouteStrategy } from '@ionic/angular';
import { SplashScreen } from '@ionic-native/splash-screen/ngx';
import { StatusBar } from '@ionic-native/status-bar/ngx';

import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module';
import { ProductService } from './services/product.service';
import { AllProductsComponent } from './components/all-products/all-products.component';
import { NgxPaginationModule } from 'ngx-pagination';
import { HttpClientModule } from '@angular/common/http';
import { ProductSelectComponent } from './components/product-select/product-select.component';
import { CartService } from './services/cart.service';
import { MsgService } from './services/msg.service';


import { FormsModule } from '@angular/forms';
import { IonicStorageModule } from '@ionic/storage';
import { MessageComponent } from './components/message/message.component';
import { AuthenticationService } from './services/authentication.service';
import { ClientService } from './services/client.service';
import { CartComponent } from './components/cart/cart.component';

@NgModule({
  declarations: [AppComponent, AllProductsComponent, CartComponent, ProductSelectComponent, MessageComponent],
  entryComponents: [],
  imports: [BrowserModule, IonicModule.forRoot(), IonicStorageModule.forRoot(), AppRoutingModule, FormsModule, HttpClientModule, NgxPaginationModule],
  providers: [
    AuthenticationService,
    ProductService,
    ClientService,
    CartService,
    MsgService,
    StatusBar,
    SplashScreen,
    { provide: RouteReuseStrategy, useClass: IonicRouteStrategy }
  ],
  bootstrap: [AppComponent]
})
export class AppModule {}
