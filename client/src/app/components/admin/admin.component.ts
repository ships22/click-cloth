import { Component, OnInit, ViewChild } from "@angular/core";
import { Admin } from "src/app/models/admin";
import { Shop } from "src/app/models/shop";
import { AdminService } from "src/app/services/admin.service";
import { AuthenticationService } from "src/app/services/authentication.service";
import { MsgService } from "src/app/services/msg.service";
import { take } from "rxjs/operators";
import { Subscription, Observable } from "rxjs";
import { ShopService } from "src/app/services/shop.service";
import { MatDialog } from "@angular/material/dialog";
import { AddShopComponent } from "./add-shop/add-shop.component";
import { AddProductComponent } from "./add-product/add-product.component";
import { Product } from "src/app/models/products/product";
import { MatTableDataSource } from "@angular/material/table";
import { MatSort } from "@angular/material/sort";
import { MatPaginator } from "@angular/material/paginator";
import { ProductService } from "src/app/services/product.service";
import { Router } from "@angular/router";
import { Reservation } from 'src/app/models/reservation';
import { ReservationService } from 'src/app/services/reservation.service';

@Component({
  selector: "app-admin",
  templateUrl: "./admin.component.html",
  styleUrls: ["./admin.component.sass"],
})
export class AdminComponent implements OnInit {


  // product management -

  productSearchKey: string;
  subscrition: Subscription;
  admin: Admin;
  shop: Shop;
  productListInIt: Product[];
  shopName: string;
  subscription: Subscription;
  productMatList: MatTableDataSource<Product>;
  searchKey: string;
  columns: string[] = [
    "id",
    "name",
    "description",
    "price",
    "discount",
    "image",
    "stocks",
    "options",
  ];

  @ViewChild(MatSort, { static: true }) sort: MatSort;
  @ViewChild(MatPaginator) paginator: MatPaginator;

  // reservation management -
  reservationSearch: string;
  reservationListInIt: any[];
  reservationSubscription: Subscription;

  reservationMatList: MatTableDataSource<Reservation>;
  resColumns: string[] = [
    "reservation_id",
    "reference",
    "product"
    // "client",
    // "total",
    // "discount",
    // "image",
    // "size",
    // "quantité",
    // "options",
  ];

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
  adminHttpService$ = new Observable<Admin[]>();
  ngOnInit(): void {
    this.getAdminDetails();
    this.shopService.refresh$.subscribe(() => this.getAdminDetails());
    this.productService.refresh$.subscribe((response) =>
      this.getAdminDetails()
    );
  }

  getAdminDetails() {
    let adminEmail = this.authenticationService.getEmail();
    if (adminEmail) {
      this.subscrition = this.adminService
        .getAdminByEmail(adminEmail)
        .pipe(take(1))
        .subscribe(
          (response) => (
            (this.admin = response),
            console.log("test admin :", response),
            this.getAllProductByShop(response.shops[0].shop_id)
            // this.getAllReservationByShop(response.shops[0].shop_id)
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
  onCreateShop() {
    this.dialog.open(AddShopComponent);
  }
  getAllProductByShop(shop_id) {
    this.productService
      .getProductByShopId(shop_id)
      .pipe(take(1))
      .subscribe((response) => {
        this.productListInIt = response;
        console.log('test all prod', response);
        (this.productMatList = new MatTableDataSource(this.productListInIt)),
          (this.productMatList.sort = this.sort);
        this.productMatList.paginator = this.paginator;
      });
  }
  // getAllReservationByShop(shop_id) {
  //   this.reseravtionService
  //   .getReservationsByShop(shop_id)
  //   .pipe(take(1))
  //   .subscribe((response) => {
  //     this.reservationListInIt = response;
  //     console.log('test reservation :', response);

  //     this.reservationMatList = new MatTableDataSource(this.reservationListInIt);
  //     this.reservationMatList.sort = this.sort;
  //     this.reservationMatList.paginator = this.paginator;
  //   })
  // }
  onCreateProduct() {
    this.dialog.open(AddProductComponent);
  }
  filterProduct() {
    this.productMatList.filter = this.productSearchKey.trim().toLocaleLowerCase();
  }
  onEdit(product) {
    this.router.navigate(["/edit_product", product.id]);
  }
  onDelete(id) {
    this.productService.deleteProduct(id).pipe(take(1)).subscribe();
  }
  resetPasswordRequest() {
    const adminEmail = this.authenticationService.getEmail();
    this.authenticationService
      .resetPasswordRequest(adminEmail)
      .pipe(take(1))
      .subscribe((response) => {
        if (response.operationResult == "SUCCESS") {
          this.messageService.sendMessage(
            "Un lien de réinitialisation de mot de passe a été envoyé à votre adresse mèl."
          );
        } else {
          this.messageService.sendMessage("Problème technique");
        }
      });
  }
}
