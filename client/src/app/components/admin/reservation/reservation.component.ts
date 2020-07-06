import { Component, OnInit, ViewChild } from "@angular/core";
import { take } from "rxjs/operators";
import { MatTableDataSource } from "@angular/material/table";
import { MatSort } from "@angular/material/sort";
import { MatPaginator } from "@angular/material/paginator";
import { Reservation } from "src/app/models/reservation";
import { AdminService } from "src/app/services/admin.service";
import { MsgService } from "src/app/services/msg.service";
import { AuthenticationService } from "src/app/services/authentication.service";
import { Router } from "@angular/router";
import { ShopService } from "src/app/services/shop.service";
import { ProductService } from "src/app/services/product.service";
import { ReservationService } from "src/app/services/reservation.service";
import { MatDialog } from "@angular/material/dialog";
import { Subscription } from "rxjs";
import { Admin } from "src/app/models/admin";

@Component({
  selector: "app-reservation",
  templateUrl: "./reservation.component.html",
  styleUrls: ["./reservation.component.sass"],
})
export class ReservationComponent implements OnInit {
  subscription: Subscription;
  admin: Admin;
  shopId: number;
  searchKey: string;

  @ViewChild(MatSort, { static: true }) sort: MatSort;
  @ViewChild(MatPaginator) paginator: MatPaginator;

  // reservation management -
  reservationSearch: string;
  reservationListInIt: any[];
  reservationMatList: MatTableDataSource<Reservation>;
  resColumns: string[] = [
    "reservation_id",
    "reference",
    "product",
    "quantity",
    "total",
    "discount",
    "status",
    "client",
    "date",
  ];
  status: any[] = [{ st: "en attente" }, { st: "confirmé" }, { st: "annulé" }];
  selectedStatus: string;

  constructor(
    private adminService: AdminService,
    private messageService: MsgService,
    private authenticationService: AuthenticationService,
    private router: Router,
    private shopService: ShopService,
    private productService: ProductService,
    private reseravtionService: ReservationService,
    private dialog: MatDialog
  ) {}
  ngOnInit(): void {
    this.getAdminDetails();
    this.shopService.refresh$.subscribe(() => this.getAdminDetails());
    this.productService.refresh$.subscribe((response) =>
      this.getAdminDetails()
    );
    this.reseravtionService.refresh$.subscribe((response) =>
      this.getAllReservationByShop(this.shopId)
    );
  }

  getAdminDetails() {
    let adminEmail = this.authenticationService.getEmail();
    if (adminEmail) {
      this.subscription = this.adminService
        .getAdminByEmail(adminEmail)
        .pipe(take(1))
        .subscribe(
          (response) => (
            (this.admin = response),
            (this.shopId = response.shops[0].shop_id),
            this.getAllReservationByShop(response.shops[0].shop_id)
            // (this.productListInIt = response.shops[0]?.productList),
            // (this.productMatList = new MatTableDataSource(
            //   this.productListInIt
            // )),
            // (this.productMatList.sort = this.sort),
            // (this.productMatList.paginator = this.paginator)
          ),
          (error) => this.messageService.sendMessage("Problème technique")
        );
    }
  }
  getAllReservationByShop(shop_id) {
    this.reseravtionService
      .getReservationsByShop(shop_id)
      .pipe(take(1))
      .subscribe((response) => {
        this.reservationListInIt = response;
        console.log("test reservation :", response);

        this.reservationMatList = new MatTableDataSource(
          this.reservationListInIt
        );
        this.reservationMatList.sort = this.sort;
        this.reservationMatList.paginator = this.paginator;
      });
  }
  // updateStatus(status : string, reservationId: number, stockId: number, quantity: number) {
  //   console.log('test update st');

  // }
  updateStatus(status: string, reservation) {
    console.log(
      "test update st",
      status,
      "test res: ",
      reservation.reservation_id,
      reservation.product.stocks[0].id,
      reservation.quantity
    );
    this.reseravtionService.updateStatusAndStock(
      status,
      reservation.reservation_id,
      reservation.product.stocks[0].id,
      reservation.quantity
    ).subscribe(res => console.log('test update :', res)
    )
  }
  filterProduct() {
    console.log("tst filter");
  }
}
