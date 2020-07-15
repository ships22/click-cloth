import { Component, OnInit } from "@angular/core";
import { CartService } from "src/app/services/cart.service";
import { AuthenticationService } from "src/app/services/authentication.service";

import { MsgService } from "src/app/services/msg.service";
import { Router } from "@angular/router";
import { ClientService } from "src/app/services/client.service";

@Component({
  selector: "app-cart",
  templateUrl: "./cart.component.html",
  styleUrls: ["./cart.component.scss"],
})
export class CartComponent implements OnInit {
  cartItems: any[] = [];
  
  emptyCart: boolean = true;
  totalToPay: any = 0;
  p: number = 1;

  constructor(
    private authentication: AuthenticationService,
    private clientService: ClientService,
    public msgService: MsgService,
    private cartService: CartService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.cartService.numberOfItems$.subscribe((data) => {
      data.then((value) => {
        this.cartItems = value;
        if (this.cartItems?.length) {
          let total = 0;
          for (let i = 0; i < this.cartItems.length; i++) {
            this.cartItems[i].sub_total =
              this.cartItems[i].qty * this.cartItems[i].price;
            total += this.cartItems[i].sub_total;
          }
          this.emptyCart = false;
          this.totalToPay = total;
        }
      });
    });
  }
  decrase(item) {
    this.cartService.decrease(item);
    if (this.cartItems.length == 0) {
      this.emptyCart = true;
    }
  }
  delete(item) {
    this.cartService.delete(item);
    if (this.cartItems.length == 0) {
      this.emptyCart = true;
    }
  }
  reserve(item) {
    console.log("id to reserve :", item);
    if (!this.authentication.isLoggedIn()) {
      console.log("user not logged in ");
      this.router.navigate(["login"]);
    } else {
      console.log("logged in user");
      let clientEmail = this.authentication.getEmail();
      this.clientService.getClientByEmail(clientEmail).subscribe((response) => {
        console.log("test client by mail :", response);
        let reservation = {
          reference: item.productRef,
          total: item.sub_total,
          status: "en attente",
          quantity: item.qty,
          client: {
            id: response.id,
          },
        };
        this.cartService
          .reserve(reservation, item.id, item.shop.shop_id)
          .subscribe((response) => {
            console.log("item reserved :", response);
            this.delete(item);
            this.msgService.sendMessage("Merci pour votre réservation.");
          });
      });
    }
  }
}
