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
import {MatButtonModule} from '@angular/material/button';
import {MatProgressSpinnerModule} from '@angular/material/progress-spinner';
import { CarouselModule } from 'ngx-owl-carousel-o';
import { FooterComponent } from './components/footer/footer.component';
import { LoaderComponent } from './components/shared/loader/loader.component';
import { LoadingService } from './services/loading.service';
import { MsgService } from './services/msg.service';
import { MessageComponent } from './components/message/message.component';

const MaterialComponents = [MatButtonModule, MatProgressSpinnerModule];

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
  ],
  imports: [BrowserModule, AppRoutingModule, FormsModule, HttpClientModule, BrowserAnimationsModule, MaterialComponents, CarouselModule],
  providers: [AuthenticationService, AdminService, ClientService, LoadingService, MsgService],
  bootstrap: [AppComponent],
})
export class AppModule {}
