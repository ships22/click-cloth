import { Injectable } from "@angular/core";
import { BehaviorSubject, Observable } from "rxjs";
import { MsgService } from "./msg.service";
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Router } from "@angular/router";
import { environment } from "src/environments/environment";

@Injectable({
  providedIn: "root",
})
export class CartService {
  private base_url = environment.api_url;

  checkItemsSubject: BehaviorSubject<any> = new BehaviorSubject<any>(
    this.getAllItem()
  );
  numberOfItems$ = this.checkItemsSubject.asObservable();
  private token;
  contents: any = [];
  KEY: string = "c&cCart";
  constructor(
    private httpClient: HttpClient,
    private messageService: MsgService,
    private router: Router
  ) {
    this.token = 'Bearer ' + this.getToken();
  }

  getAllItem() {
    let contents = localStorage.getItem("c&cCart");
    if (contents) {
      return (this.contents = JSON.parse(contents));
    }
    return null;
  }
  getToken():string {
    return localStorage.getItem('token');
  }

  addToCart(item, qty) {
    this.getAllItem();
    this.addOrIncrease(item, qty);
  }
  async cartSync(KEY, contents) {
    let _cart = JSON.stringify(contents);
    await localStorage.setItem(KEY, _cart);
  }
  addOrIncrease(item, qty) {
    let exists = this.contents.find((product) => product.id == item.id);
    if (!exists) {
      item.qty = qty;
      this.contents.push(item);
    } else {
      exists.qty = +exists.qty + qty;
    }
    this.cartSync(this.KEY, this.contents);
    return this.checkItemsSubject.next(this.getAllItem());
  }
  decrease(item) {
    this.getAllItem();
    let itemIndex = this.contents.findIndex(
      (product) => product.id === item.id
    );
    this.contents[itemIndex].qty--;
    if (this.contents[itemIndex].qty == 0) {
      this.contents.splice(itemIndex, 1);
    }

    // let exists = this.contents.find(product => product.id == item.id);
    // if(exists && exists.qty) {
    //   exists.qty--;
    //   if(exists.qty == 0) this.contents = this.contents.filter(data => data.id == exists.id);
    // }
    this.cartSync(this.KEY, this.contents);
    return this.checkItemsSubject.next(this.getAllItem());
  }

  delete(item) {
    let itemIndex = this.contents.findIndex(
      (product) => product.id === item.id
    );
    this.contents.splice(itemIndex, 1);
    this.cartSync(this.KEY, this.contents);
    return this.checkItemsSubject.next(this.getAllItem());

    //   let exists = this.contents.find(product => product.id == item.id);
    //   if(exists) this.contents = this.contents.filter(data => data.id == exists.id);
    //   this.cartSync(this.KEY, this.contents);
    // return this.checkItemsSubject.next(this.getAllItem());
  }

  reserve(reservation: any, product_id, shop_id): Observable<any> {
    const headers = new HttpHeaders().set("Authorization", this.token );
    return this.httpClient.post<any>(
      this.base_url + "do_reservation/" + product_id + "/shop/" + shop_id,
      reservation, {headers}
    );
  }
}
