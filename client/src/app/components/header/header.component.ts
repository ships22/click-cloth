import { Component, OnInit } from "@angular/core";
import { AuthenticationService } from "src/app/services/authentication.service";
import { RoleGuardService } from "src/app/services/role-guard.service";
import { CartService } from 'src/app/services/cart.service';

@Component({
  selector: "app-header",
  templateUrl: "./header.component.html",
  styleUrls: ["./header.component.sass"],
})
export class HeaderComponent implements OnInit {
  isAdmin$;
  isLoggedIn$;
  isSuperAdmin$;
  totalItemsAdded$;

  constructor(
    public authentication: AuthenticationService,
    public roleGuardService: RoleGuardService,
    public cartService: CartService
  ) {}

  ngOnInit(): void {
    this.isLoggedIn$ = this.authentication.checkLogIn$;
    this.isAdmin$ = this.authentication.checkAdmin$;
    this.isSuperAdmin$ = this.authentication.checkSuperAdmin$;
    this.cartService.numberOfItems$
    .subscribe(res => console.log('test cart itams :', res)
    )
  }
  logout() {
    this.authentication.signOut();
  }
}
