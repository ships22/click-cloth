import { Component, OnInit } from '@angular/core';
import { NgxPaginationModule } from 'ngx-pagination';

@Component({
  selector: 'app-all-products',
  templateUrl: './all-products.component.html',
  styleUrls: ['./all-products.component.sass']
})
export class AllProductsComponent implements OnInit {
  p: number = 1;
  collection = [];

  constructor() {
    for(let i = 1; i <= 200; i++) {
      let obj = { 'Name' : `User${i}`, 'Id' : `Uid${i}`}
      this.collection.push(obj);
    }
   }

  ngOnInit(): void {
  }

}
