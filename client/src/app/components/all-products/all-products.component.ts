import { Component, OnInit } from '@angular/core';
import { NgxPaginationModule } from 'ngx-pagination';
import { ProductService } from 'src/app/services/product.service';
import { Product } from 'src/app/models/products/product';
import { take } from 'rxjs/operators';
import { Router } from '@angular/router';
import { CategoryService } from 'src/app/services/category.service';

@Component({
  selector: 'app-all-products',
  templateUrl: './all-products.component.html',
  styleUrls: ['./all-products.component.sass']
})
export class AllProductsComponent implements OnInit {
  categories = [];
  selectedCat: '';
  p: number = 1;
  collection: any[] = [];
  filteredProduct: any[] = [];

  constructor(private productService: ProductService, private router: Router,
    private categoryService: CategoryService) {
   }

  ngOnInit(): void {
    this.getAllProducts();
    this.getAllCategory();
  }

  getAllProducts() {
    this.productService.getAllProducts()
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
  getAllCategory() {
    this.categoryService.getAllCategory()
    .subscribe(data => {
      this.categories = data;
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
