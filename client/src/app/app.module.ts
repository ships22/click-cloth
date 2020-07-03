import { BrowserModule } from "@angular/platform-browser";
import { FormsModule } from "@angular/forms";
import { HttpClientModule } from "@angular/common/http";
import { NgModule } from "@angular/core";
import { NgxPaginationModule } from "ngx-pagination";

import { AppRoutingModule } from "./app-routing.module";
import { AppComponent } from "./app.component";
import { LoginComponent } from "./components/login/login.component";
import { RegistrationComponent } from "./components/registration/registration.component";
import { TestComponent } from "./components/test/test.component";
import { AuthenticationService } from "./services/authentication.service";
import { AdminService } from "./services/admin.service";
import { ClientService } from "./services/client.service";
import { HomeComponent } from "./components/home/home.component";
import { HeaderComponent } from "./components/header/header.component";
import { BrowserAnimationsModule } from "@angular/platform-browser/animations";
import { MatButtonModule } from "@angular/material/button";
import { MatProgressSpinnerModule } from "@angular/material/progress-spinner";
import { CarouselModule } from "ngx-owl-carousel-o";
import { FooterComponent } from "./components/footer/footer.component";
import { LoaderComponent } from "./components/shared/loader/loader.component";
import { LoadingService } from "./services/loading.service";
import { MsgService } from "./services/msg.service";
import { MessageComponent } from "./components/message/message.component";
import { AdminComponent } from "./components/admin/admin.component";
import { SupAdminComponent } from "./components/sup-admin/sup-admin.component";
import { MatTableModule } from "@angular/material/table";
import { MatPaginatorModule } from "@angular/material/paginator";
import { MatSortModule } from "@angular/material/sort";
import { MatIconModule } from "@angular/material/icon";
import { MatFormFieldModule } from "@angular/material/form-field";
import { MatInputModule } from "@angular/material/input";
import { MaterialFileInputModule } from "ngx-material-file-input";
import { MatTabsModule } from "@angular/material/tabs";
import { AddAdminComponent } from "./components/sup-admin/add-admin/add-admin.component";
import { MatDialogModule, MatDialogRef } from "@angular/material/dialog";
import { EditAdminComponent } from "./components/sup-admin/edit-admin/edit-admin.component";
import { AddShopComponent } from "./components/admin/add-shop/add-shop.component";
import { ShopService } from "./services/shop.service";
import { AddProductComponent } from "./components/admin/add-product/add-product.component";
import { EditProductComponent } from "./components/admin/edit-product/edit-product.component";
import { MatSelectModule } from "@angular/material/select";
import { MatRadioModule } from "@angular/material/radio";
import { AllProductsComponent } from "./components/all-products/all-products.component";
import { from } from "rxjs";
import { LadiesComponent } from "./components/ladies/ladies.component";
import { GentsComponent } from "./components/gents/gents.component";
import { ChildrenComponent } from "./components/children/children.component";
import { ProductSelectComponent } from "./components/product-select/product-select.component";
import { CartService } from './services/cart.service';

const MaterialComponents = [
  MatButtonModule,
  MatProgressSpinnerModule,
  MatIconModule,
  MatFormFieldModule,
  MatInputModule,
  MatSelectModule,
  MatTableModule,
  MatPaginatorModule,
  MatSortModule,
  MatDialogModule,
  MatTabsModule,
  MatRadioModule,
  MaterialFileInputModule,
];

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegistrationComponent,
    TestComponent,
    HomeComponent,
    HeaderComponent,
    FooterComponent,
    LoaderComponent,
    MessageComponent,
    AdminComponent,
    SupAdminComponent,
    AddAdminComponent,
    EditAdminComponent,
    AddShopComponent,
    AddProductComponent,
    EditProductComponent,
    AllProductsComponent,
    LadiesComponent,
    GentsComponent,
    ChildrenComponent,
    ProductSelectComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    BrowserAnimationsModule,
    NgxPaginationModule,
    CarouselModule,
    MaterialComponents,
  ],
  providers: [
    AuthenticationService,
    AdminService,
    ClientService,
    CartService,
    LoadingService,
    MsgService,
    ShopService,
    {
      provide: MatDialogRef,
      useValue: {},
    },
  ],
  bootstrap: [AppComponent],
  entryComponents: [AddAdminComponent],
})
export class AppModule {}
