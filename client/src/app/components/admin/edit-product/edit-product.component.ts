import { Component, OnInit } from "@angular/core";
import { Product } from "src/app/models/products/product";
import { Router, ActivatedRoute } from "@angular/router";
import { MsgService } from "src/app/services/msg.service";
import { ProductService } from "src/app/services/product.service";
import { take } from "rxjs/operators";
import { Stock } from "src/app/models/products/stock";

@Component({
  selector: "app-edit-product",
  templateUrl: "./edit-product.component.html",
  styleUrls: ["./edit-product.component.sass"],
})
export class EditProductComponent implements OnInit {
  id: number;
  newImage: any = null;
  product: Product = {
    id: null,
    name: null,
    description: null,
    productRef: null,
    price: null,
    discount: null,
    categories: null,
    stock: null,
    image: null,
    shop_id_shop: null,
    shop_admin_id_admin: null,
  };

  private editedProduct: any = {};
  stock: Stock = {
    id: null,
    quantite: null,
    size: null,
    colour: null,
  };
  sizes: any[] = [
    { size: "XS" },
    { size: "S" },
    { size: "M" },
    { size: "L" },
    { size: "XL" },
    { size: "XXL" },
  ];

  constructor(
    private router: Router,
    private productService: ProductService,
    private massageService: MsgService,
    private activatedRoute: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.activatedRoute.paramMap.subscribe((parametarMap) => {
      this.id = +parametarMap.get("id");
      this.getProductById(this.id);
    });
  }
  getProductById(id) {
    this.productService
      .getProductById(id)
      .pipe(take(1))
      .subscribe(
        (response) => (
          (this.product = response), (this.stock = response.stock[0])
        )
      );
  }
  submit(product) {
    let productData = new FormData();
    if (this.newImage != null) {
      productData.append(
        "image",
        this.newImage.files[0],
        this.newImage.files[0].name
      );
    } else {
      const byteCharacters = atob(this.product.image);
      const byteNumbers = new Array(byteCharacters.length);
      for (let i = 0; i < byteCharacters.length; i++) {
        byteNumbers[i] = byteCharacters.charCodeAt(i);
      }
      const byteArray = new Uint8Array(byteNumbers);
      const contentType = "image/jpeg";
      const blob = new Blob([byteArray], { type: contentType });
      productData.append("image", blob, "image");
    }
    // product.image = null;
    this.editedProduct.id = this.product.id;
    this.editedProduct.name = product.name;
    this.editedProduct.description = product.description;
    this.editedProduct.productRef = product.productRef;
    this.editedProduct.price = product.price;
    this.editedProduct.discount = product.discount;
    product.shop_id = 1;

    // this.stock.size = product.size;
    // this.stock.quantite = product.quantite;
    // this.stock.colour = "red";

    productData.append("product", JSON.stringify(this.editedProduct));
    productData.append("stock", JSON.stringify(this.stock));
    console.log("test here"),
      this.productService.editProduct(productData, product.shop_id).subscribe(
        //   response => {
        //     console.log("test edit product :", response);
        //     this.router.navigate(["/admin"]);
        // }
        (response) => (
          console.log("test edit product :", response),
          this.router.navigate(["/admin"])
        ),
        (error) =>
          this.massageService.sendMessage(
            "Une erreur s'est produite. Veuillez réessayer"
          )
      );
  }
}
