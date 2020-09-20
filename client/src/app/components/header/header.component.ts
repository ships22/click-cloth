import { Component, OnInit } from "@angular/core";
import { AuthenticationService } from "src/app/services/authentication.service";
import { CartService } from 'src/app/services/cart.service';
import { RoleGuardService } from "src/app/services/role-guard.service";


@Component({
  selector: "app-header",
  templateUrl: "./header.component.html",
  styleUrls: ["./header.component.sass"],
})
export class HeaderComponent implements OnInit {
  isAdmin$;
  isLoggedIn$;
  isSuperAdmin$;
  totalItemsAdded = 0;
  cartItems: any = [];

  constructor(
    public authentication: AuthenticationService,
    public roleGuardService: RoleGuardService,
    public cartService: CartService
  ) {}

  ngOnInit(): void {
    this.isLoggedIn$ = this.authentication.checkLogIn$;
    this.isAdmin$ = this.authentication.checkAdmin$;
    this.isSuperAdmin$ = this.authentication.checkSuperAdmin$;

    this.cartService.numberOfItems$.subscribe((data) => {
      if (data) {
        let qty = 0;
        for (let i = 0; i < data.length; i++) {
          qty += data[i].qty;
        }
        this.totalItemsAdded = qty;
      }
    });
  }

  logout() {
    this.authentication.signOut();
  }
  closeBar() {
    let checkBox = document.getElementById('nav-toggler') as HTMLInputElement;
    checkBox.checked = false;
    // checkBox.checked
    console.log('test menu closing', checkBox);

  }
}
