import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-add-product',
  templateUrl: './add-product.component.html',
  styleUrls: ['./add-product.component.sass']
})
export class AddProductComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }
  submit(product) {
    console.log('test add pr');
    
  }

}
