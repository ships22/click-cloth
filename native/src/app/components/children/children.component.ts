import { Component, OnInit } from '@angular/core';
import { NgxPaginationModule } from 'ngx-pagination';
import { ProductService } from 'src/app/services/product.service';
import { Product } from 'src/app/models/products/product';
import { take } from 'rxjs/operators';
import { Router } from '@angular/router';

@Component({
  selector: 'app-children',
  templateUrl: './children.component.html',
  styleUrls: ['./children.component.scss'],
})
export class ChildrenComponent implements OnInit {
  categories = [];
  selectedCat: '';
  p: number = 1;
  collection: any[] = [];

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
      })
    })
  }

  filter(selectedCat) {
    // let filteredDvds = this.dvds.filter((dvd) => dvd.cat === selectedCat);
    // if (selectedCat) {
    //   return this.dvdDisplay = filteredDvds;
    // } else if (selectedCat.length === 0) {
    //   return this.dvdDisplay = this.dvds;
    // }
    console.log('test cat :' , selectedCat);
    
  }
  selectProduct(productRef) {
    this.router.navigate(["/tabs/product_select", productRef]);
  }

}
