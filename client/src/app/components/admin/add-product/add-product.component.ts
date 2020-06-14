import { Component, OnInit, Injector } from '@angular/core';
import { ShopService } from 'src/app/services/shop.service';
import { MsgService } from 'src/app/services/msg.service';
import { AuthenticationService } from 'src/app/services/authentication.service';
import { AdminService } from 'src/app/services/admin.service';
import { MatDialogRef } from '@angular/material/dialog';
import { take } from 'rxjs/operators';
import { ProductService } from 'src/app/services/product.service';
import { Product } from 'src/app/models/products/product';

@Component({
  selector: 'app-add-product',
  templateUrl: './add-product.component.html',
  styleUrls: ['./add-product.component.sass']
})
export class AddProductComponent implements OnInit {

  private dialogRef = null;
  private adminId: number;
  private shopId: number;
  private productList: Product[];
  
  constructor(
    private injector: Injector,
    private shopService: ShopService,
    private productService: ProductService,
    public messageService: MsgService,
    private authenticationService: AuthenticationService,
    private adminService: AdminService,
  ) {
    this.dialogRef = this.injector.get(MatDialogRef, null);
  }

  ngOnInit(): void {
    this.getAdminId();
  }
  submit(product) {
    console.log('test new product :', product.image.files[0]);
    let imageData = new FormData();
    imageData.append('image',product.image.files[0], product.image.files[0].name);
    if (product.name != "" && product.description!= "" && product.price != "") {
      product.shop_id_shop = this.shopId;
      product.shop_admin_id_admin = this.adminId;
      product.image = null;
      imageData.append('product', JSON.stringify(product));
      this.productService.addProduct(imageData)
      .subscribe(
        response => (console.log('test add product :', response), this.dialogRef.close()),
        (error) => {
          this.messageService.sendMessage(
            "Une erreur s'est produite. Veuillez réessayer"
          ),
            setTimeout(() => {
              this.dialogRef.close();
            }, 3000)}
      )
    } else {
      this.messageService.sendMessage("Tous les chams sont obligatoire!");
    }
    
  }

  getAdminId() {
    let adminEmail = this.authenticationService.getEmail();
    if (adminEmail) {
      this.adminService
        .getAdminByEmail(adminEmail)
        .pipe(take(1))
        .subscribe
          (response => {
            this.adminId = response.id;
            if(response.shops.length){
            this.shopService.getShopByAdmin(response.id)
            .subscribe(shop => this.shopId = shop.shop_id)
            }
          },
          error => this.messageService.sendMessage("Problème technique")
        );
    }
  }
}
