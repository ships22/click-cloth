import { Component } from "@angular/core";
import { NavController } from "@ionic/angular";
import { CartService } from "../services/cart.service";

@Component({
  selector: "app-home",
  templateUrl: "home.page.html",
  styleUrls: ["home.page.scss"],
})
export class HomePage {
  splash = true;
  tabBarElement: any;
  totalItemsAdded: number = 0;
  constructor(public navCtrl: NavController, private cartService: CartService) {
    //this.tabBarElement = document.querySelector('.tabbar');
    //this.tabBarElement.style.display = 'none';
    setTimeout(() => {
      this.splash = false;
      //this.tabBarElement.style.display = 'flex';
    }, 4000);
    this.syncItemOfcart();
  }

  syncItemOfcart() {
    this.cartService.numberOfItems$.subscribe((data) => {
      data.then((value) => {
        if (value) {
          let qty = 0;
          for (let i = 0; i < value.length; i++) {
            qty += value[i].qty;
          }
          this.totalItemsAdded = qty;
        }
      });
    });
  }
}
