import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { ProductService } from 'src/app/services/product.service';
import { MsgService } from 'src/app/services/msg.service';
import { take } from 'rxjs/operators';
import { CartService } from 'src/app/services/cart.service';

@Component({
  selector: 'app-product-select',
  templateUrl: './product-select.component.html',
  styleUrls: ['./product-select.component.sass']
})
export class ProductSelectComponent implements OnInit {

  productRef: string;
  products: any[] = [];
  product: any = {};
  quantity: any = null;
  productOfSize: any = {};

  constructor(private router: Router,
    private productService: ProductService,
    private massageService: MsgService,
    private activatedRoute: ActivatedRoute,
    private cartService: CartService) { }

  ngOnInit(): void {
    this.activatedRoute.paramMap.subscribe((parametarMap) => {
      this.productRef = parametarMap.get("productRef");
      this.getAllProductByRef(this.productRef);
    });
  }

  getAllProductByRef(productRef) {
    this.productService.getAllProductsByRef(productRef)
    .pipe(take(1))
    .subscribe(response => {
      console.log('test prod by ref :', response);
      this.products = response;
      this.product = this.products[0];
    })
  }
  addToCart(product) {
    if(!this.quantity || !this.productOfSize) {
      this.massageService.sendMessage('Tous les champs sont obligatoire');
    } else if(this.quantity > this.productOfSize.stock[0].quantite) {
      this.massageService.sendMessage(`Il y que ${this.productOfSize.stock[0].quantite} disponible pour l'instant.`);
    } else {
      console.log('ok');
      this.cartService.addToCart(product);
    }
  }

}
