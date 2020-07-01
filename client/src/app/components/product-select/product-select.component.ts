import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { ProductService } from 'src/app/services/product.service';
import { MsgService } from 'src/app/services/msg.service';
import { take } from 'rxjs/operators';

@Component({
  selector: 'app-product-select',
  templateUrl: './product-select.component.html',
  styleUrls: ['./product-select.component.sass']
})
export class ProductSelectComponent implements OnInit {

  productRef: string;
  products: any[] = [];
  product: any = {};
  constructor(private router: Router,
    private productService: ProductService,
    private massageService: MsgService,
    private activatedRoute: ActivatedRoute) { }

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

}
