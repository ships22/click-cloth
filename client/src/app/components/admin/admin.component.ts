import { Component, OnInit } from "@angular/core";
import { Admin } from "src/app/models/admin";
import { Shop } from "src/app/models/shop";
import { AdminService } from "src/app/services/admin.service";
import { AuthenticationService } from "src/app/services/authentication.service";
import { MsgService } from "src/app/services/msg.service";
import { take } from "rxjs/operators";
import { Subscription } from "rxjs";
import { ShopService } from 'src/app/services/shop.service';
import { MatDialog } from '@angular/material/dialog';
import { AddShopComponent } from './add-shop/add-shop.component';

@Component({
  selector: "app-admin",
  templateUrl: "./admin.component.html",
  styleUrls: ["./admin.component.sass"],
})
export class AdminComponent implements OnInit {
  subscrition: Subscription;
  admin: Admin;
  shop: Shop;
  shopName: string;

  constructor(
    private adminService: AdminService,
    private messageService: MsgService,
    private authenticationService: AuthenticationService,
    private shopService: ShopService,
    private dialog: MatDialog,
  ) {}

  ngOnInit(): void {
    this.getAdminDetails();
    this.shopService.refresh$
    .subscribe(() => this.getAdminDetails());
  }

  getAdminDetails() {
    let adminEmail = this.authenticationService.getEmail();
    if (adminEmail) {
      this.subscrition = this.adminService
        .getAdminByEmail(adminEmail)
        .pipe(take(1))
        .subscribe(
          (response) => (this.admin = response),
          (error) => this.messageService.sendMessage("Problème technique")
        );
    }
  }
  onCreate() {
    this.dialog.open(AddShopComponent);
  }
}
