import { Component, OnInit } from '@angular/core';
import { take } from 'rxjs/operators';
import { ProductService } from 'src/app/services/product.service';

@Component({
  selector: 'app-ladies',
  templateUrl: './ladies.component.html',
  styleUrls: ['./ladies.component.sass']
})
export class LadiesComponent implements OnInit {

  categories = [];
  selectedCat: '';
  p: number = 1;
  collection: any[] = [];

  constructor(private productService: ProductService) {
   }

  ngOnInit(): void {
    this.getAllProducts();
  }

  getAllProducts() {
    this.productService.getAllProductByFemale()
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

}

