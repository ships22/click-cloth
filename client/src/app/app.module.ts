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

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegistrationComponent,
    TestComponent,
  ],
  imports: [BrowserModule, AppRoutingModule, FormsModule, HttpClientModule],
  providers: [AuthenticationService, AdminService, ClientService],
  bootstrap: [AppComponent],
})
export class AppModule {}
