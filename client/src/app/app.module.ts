import { BrowserModule } from "@angular/platform-browser";
import { FormsModule } from "@angular/forms";
import { HttpClientModule } from "@angular/common/http";
import { NgModule } from "@angular/core";

import { AppRoutingModule } from "./app-routing.module";
import { AppComponent } from "./app.component";
import { LoginComponent } from "./components/login/login.component";
import { RegistrationComponent } from "./components/registration/registration.component";
import { TestComponent } from "./components/test/test.component";
import { AuthenticationService } from "./services/authentication.service";
import { AdminService } from "./services/admin.service";
import { ClientService } from "./services/client.service";
import { HomeComponent } from './components/home/home.component';
import { HeaderComponent } from './components/header/header.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatButtonModule } from '@angular/material/button';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { CarouselModule } from 'ngx-owl-carousel-o';
import { FooterComponent } from './components/footer/footer.component';
import { LoaderComponent } from './components/shared/loader/loader.component';
import { LoadingService } from './services/loading.service';
import { MsgService } from './services/msg.service';
import { MessageComponent } from './components/message/message.component';
import { AdminComponent } from './components/admin/admin.component';
import { SupAdminComponent } from './components/sup-admin/sup-admin.component';
import { SuperAdminComponent } from './components/super-admin/super-admin.component';
import { MatTableModule } from '@angular/material/table';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatSortModule } from '@angular/material/sort';
import { MatIconModule } from '@angular/material/icon';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatInputModule} from '@angular/material/input';
import { AddAdminComponent } from './components/sup-admin/add-admin/add-admin.component';
import { MatDialogModule, MatDialogRef } from '@angular/material/dialog';
import { EditAdminComponent } from './components/sup-admin/edit-admin/edit-admin.component';

const MaterialComponents = [  MatButtonModule, 
                              MatProgressSpinnerModule, 
                              MatIconModule,
                              MatFormFieldModule,
                              MatInputModule,
                              MatTableModule, 
                              MatPaginatorModule, 
                              MatSortModule, 
                              MatDialogModule
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
    SuperAdminComponent,
    AddAdminComponent,
    EditAdminComponent,
  ],
  imports: [BrowserModule, AppRoutingModule, FormsModule, HttpClientModule, BrowserAnimationsModule, MaterialComponents, CarouselModule],
  providers: [AuthenticationService, AdminService, ClientService, LoadingService, MsgService,
  {
    provide: MatDialogRef,
    useValue: {}
  }],
  bootstrap: [AppComponent],
  entryComponents: [AddAdminComponent]
})
export class AppModule {}
