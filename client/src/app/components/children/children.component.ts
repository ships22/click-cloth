import { Component, OnInit } from '@angular/core';
import { take } from 'rxjs/operators';
import { ProductService } from 'src/app/services/product.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-children',
  templateUrl: './children.component.html',
  styleUrls: ['./children.component.sass']
})
export class ChildrenComponent implements OnInit {
  categories = [];
  selectedCat: '';
  p: number = 1;
  collection: any[] = [];
  filteredProduct: any[] = [];

  constructor(private productService: ProductService, private router: Router,) {
   }

  ngOnInit(): void {
    this.getAllProducts();
  }

  getAllProducts() {
    this.productService.getAllProductByChildren()
    .pipe(take(1))
    .subscribe(response => {
      console.log('test products call :' , response);
      this.collection = response;

      this.collection.forEach(product => {


        product.stock.forEach(stock => {
          product['inStock'] =+ stock.quantite;
        });
      });
      this.filteredProduct = this.collection;
    })
  }

  filter(selectedCat) {
    console.log('test cat :' , selectedCat);
    if(selectedCat) {
      this.collection = this.filteredProduct.filter(product => product.categories[0].name == selectedCat);
    } else {
      this.collection = this.filteredProduct;
    }
  }
  selectProduct(productRef) {
    this.router.navigate(["/product_select", productRef]);
  }

}
