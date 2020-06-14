import { Component, OnInit } from '@angular/core';
import { Product } from 'src/app/models/products/product';
import { Router, ActivatedRoute } from '@angular/router';
import { MsgService } from 'src/app/services/msg.service';
import { ProductService } from 'src/app/services/product.service';
import { take } from 'rxjs/operators';

@Component({
  selector: 'app-edit-product',
  templateUrl: './edit-product.component.html',
  styleUrls: ['./edit-product.component.sass']
})
export class EditProductComponent implements OnInit {

  id: number;
  newImage:any = null;
  product: Product = {
    id: null,
    name: null,
    description: null,
    price: null,
    discount: null,
    image: null,
    shop_id_shop: null,
    shop_admin_id_admin: null
  }

  constructor(
    private router: Router, 
    private productService: ProductService,
    private massageService: MsgService,
    private activatedRoute: ActivatedRoute 
  ) { }

  ngOnInit(): void {
    this.activatedRoute.paramMap
    .subscribe(parametarMap => {
      this.id = +parametarMap.get('id');
      this.getProductById(this.id);
    })
  }
  getProductById(id) {
    this.productService.getProductById(id)
    .pipe(take(1))
    .subscribe(response => (this.product = response)
    )
  }
  submit(product) {
    let productData = new FormData();
    if(this.newImage != null) {
       productData.append('image', this.newImage.files[0], this.newImage.files[0].name); 
    } else {
      const byteCharacters = atob(this.product.image);
      const byteNumbers = new Array(byteCharacters.length);
        for (let i = 0; i < byteCharacters.length; i++) {
          byteNumbers[i] = byteCharacters.charCodeAt(i);
        }
        const byteArray = new Uint8Array(byteNumbers);
        const contentType = 'image/jpeg';
        const blob = new Blob([byteArray], {type: contentType});
        productData.append('image', blob, 'image')
    }
      product.image = null;
      product.id = this.product.id;  
      product.shop_id_shop = this.product.shop_admin_id_admin;
      product.shop_admin_id_admin = this.product.shop_admin_id_admin;
      productData.append('product', JSON.stringify(product));
      this.productService.editProduct(productData)
      .subscribe(
        response => (console.log('test add product :', response)),
        error => (this.massageService.sendMessage("Une erreur s'est produite. Veuillez réessayer"))
        )
    
  }

}
