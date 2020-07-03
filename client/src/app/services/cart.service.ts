import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CartService {

  checkItemsSubject: BehaviorSubject<any> = new BehaviorSubject<any>(this.getAllItem());
  numberOfItems$ = this.checkItemsSubject.asObservable();

  contents: any = [];
  KEY: string = "c&cCart";
  constructor() { }

 getAllItem() {
  let contents =   localStorage.getItem('c&cCart');
    if(contents){
      return this.contents =  JSON.parse(contents);
    }
    return null;
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
    let exists = this.contents.find(product => product.id == item.id);
    if(!exists) {
      item.qty = qty;
      this.contents.push(item);
    } else {
      exists.qty =+ exists.qty + qty;
    }
    this.cartSync(this.KEY, this.contents);
  return this.checkItemsSubject.next(this.getAllItem());
  }
}
