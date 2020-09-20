import { Component, OnInit, Injector } from "@angular/core";
import { ShopService } from "src/app/services/shop.service";
import { MsgService } from "src/app/services/msg.service";
import { AuthenticationService } from "src/app/services/authentication.service";
import { AdminService } from "src/app/services/admin.service";
import { MatDialogRef } from "@angular/material/dialog";
import { take } from "rxjs/operators";
import { ProductService } from "src/app/services/product.service";
import { Product } from "src/app/models/products/product";
import { Stock } from 'src/app/models/products/stock';
import { CategoryService } from 'src/app/services/category.service';

@Component({
  selector: "app-add-product",
  templateUrl: "./add-product.component.html",
  styleUrls: ["./add-product.component.sass"],
})
export class AddProductComponent implements OnInit {
  private dialogRef = null;
  private adminId: number;
  private shopId: number;
  private productList: Product[];
  private newProduct: any = {};
  categories: any = [];
  stock: Stock = {
  id:null,
	quantite:null,
	size:null,
	colour:null,



  };
  sizes:any[] = [
    { size: "XS" },
    { size: "S" },
    { size: "M" },
    { size: "L" },
    { size: "XL" },
    { size: "XXL" }
  ];

  constructor(
    private injector: Injector,
    private shopService: ShopService,
    private productService: ProductService,
    public messageService: MsgService,
    private authenticationService: AuthenticationService,
    private adminService: AdminService,
    private categoryService: CategoryService
  ) {
    this.dialogRef = this.injector.get(MatDialogRef, null);
  }

  ngOnInit(): void {
    this.getAdminId();
    this.getAllCategory();
  }
  submit(product) {


    console.log('test pro :' , product.quantite);

    this.stock.size = product.size;
    this.stock.quantite = product.quantite;
    this.stock.colour = "red";


    this.newProduct.name = product.name;
    this.newProduct.description = product.description;
    this.newProduct.productRef = 's' + product.productRef.toLowerCase() + this.shopId;
    this.newProduct.price = product.price;
    this.newProduct.discount = product.discount;
      let productData = new FormData();
      productData.append(
      "image",
      product.image.files[0],
      product.image.files[0].name
    );
    if (
      product.name != "" &&
      product.description != "" &&
      product.price != "" &&
      product.quantite != "" &&
      product.size != "" &&
      product.category != "" &&
      product.productRef != ""
    ) {
      productData.append("product", JSON.stringify(this.newProduct));
      productData.append("stock", JSON.stringify(this.stock));
      this.productService.addProduct(productData, this.shopId, product.category).subscribe(
            (response) => (
              console.log("test add product :", response), this.dialogRef.close()
            ),
            (error) => {
              this.messageService.sendMessage(
                "Une erreur s'est produite. Veuillez réessayer"
              ),
                setTimeout(() => {
                  this.dialogRef.close();
                }, 3000);
            }
          );
    }
  }

  getAdminId() {
    let adminEmail = this.authenticationService.getEmail();
    if (adminEmail) {
      this.adminService
        .getAdminByEmail(adminEmail)
        .pipe(take(1))
        .subscribe(
          (response) => {
            this.adminId = response.id;
            console.log('tres :', response);

            if (response.shops.length) {
              this.shopService
                .getShopByAdmin(response.id)
                .subscribe((shop) => this.shopId = shop.shop_id);
            }
          },
          (error) => this.messageService.sendMessage("Problème technique")
        );
    }
  }
  getAllCategory() {
    this.categoryService.getAllCategory()
    .subscribe(data => {
      this.categories = data;
      console.log('test cat list :', this.categories);
    })
  }
}
