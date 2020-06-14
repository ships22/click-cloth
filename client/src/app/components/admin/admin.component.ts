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

@Component({
  selector: "app-admin",
  templateUrl: "./admin.component.html",
  styleUrls: ["./admin.component.sass"],
})
export class AdminComponent implements OnInit {
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
    "options",
  ];

  @ViewChild(MatSort, { static: true }) sort: MatSort;
  @ViewChild(MatPaginator) paginator: MatPaginator;

  constructor(
    private adminService: AdminService,
    private messageService: MsgService,
    private authenticationService: AuthenticationService,
    private shopService: ShopService,
    private productService: ProductService,
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
            (this.productListInIt = response.shops[0]?.productList),
            (this.productMatList = new MatTableDataSource(
              this.productListInIt
            )),
            (this.productMatList.sort = this.sort),
            (this.productMatList.paginator = this.paginator)
          ),
          (error) => this.messageService.sendMessage("Problème technique")
        );
    }
  }
  onCreateShop() {
    this.dialog.open(AddShopComponent);
  }
  onCreateProduct() {
    console.log("on ce pr");
    this.dialog.open(AddProductComponent);
  }
  filterProduct() {
    console.log("filter pr");
  }
  onEdit(admin) {
    // this.router.navigate(["/edit_admin", admin.id]);
    console.log('test edit...');
    
  }
  onDelete(id) {
    this.adminService.deleteAdmin(id).pipe(take(1)).subscribe();
    this.productService.deleteProduct(id).pipe(take(1))
    .subscribe();
  }

  // ngOnDestroy() {
  //   this.subscription.unsubscribe();
  // }
}
