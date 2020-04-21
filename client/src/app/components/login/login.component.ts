import { Component, OnInit } from "@angular/core";
import { AuthenticationService } from "../../services/authentication.service";

@Component({
  selector: "app-login",
  templateUrl: "./login.component.html",
  styleUrls: ["./login.component.sass"],
})
export class LoginComponent implements OnInit {
  userToLogin: any = {};
  message: any;
  loading = false;

  constructor(private authenticationService: AuthenticationService) {}

  ngOnInit() {}

  signIn() {
    const id: string = this.userToLogin.email;
    const password: string = this.userToLogin.password;
    this.authenticationService.signIn(id, password).subscribe(
      (res) => {
        console.log(
          "res from login component : ",
          this.authenticationService.decoded_token
        );
      },
      (err) => {
        console.log("err : ", err);

        this.message = "id or mdtp not good";
        setTimeout(() => (this.message = ""));
      }
    );
  }
}
