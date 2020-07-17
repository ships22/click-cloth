import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { ProductService } from 'src/app/services/product.service';

import { take } from 'rxjs/operators';
import { CartService } from 'src/app/services/cart.service';
import { MsgService } from 'src/app/services/msg.service';

@Component({
  selector: 'app-product-select',
  templateUrl: './product-select.component.html',
  styleUrls: ['./product-select.component.scss']
})
export class ProductSelectComponent implements OnInit {

  productRef: string;
  products: any[] = [];
  product: any = {};
  quantity: any = null;
  productOfSize: any = {};

  constructor(private router: Router,
    private productService: ProductService,
    private cartService: CartService,   
    public messageService: MsgService,
    private activatedRoute: ActivatedRoute,
    ) { }

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
  addToCart() {
    if(!this.quantity || !this.productOfSize) {
     this.messageService.sendMessage('Tous les champs sont obligatoire');
    } else if(this.quantity && this.productOfSize.stock == undefined) {
      this.messageService.sendMessage('Sélectionnez votre taille s\'il vous plaît');
    } else if(this.quantity > this.productOfSize.stock[0].quantite) {
      this.messageService.sendMessage(`Il y que ${this.productOfSize.stock[0].quantite} disponible pour l'instant.`);
    } else {
      this.cartService.addToCart(this.productOfSize, this.quantity);
      this.messageService.sendMessage('Ajouté au panier');
      this.router.navigate(['tabs/all-products']);
    }  
  }

}
