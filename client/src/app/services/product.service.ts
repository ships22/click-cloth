import { Injectable } from '@angular/core';
import { Observable, Subject } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { tap } from 'rxjs/operators';
import { Product } from '../models/products/product';

@Injectable({
  providedIn: 'root'
})
export class ProductService {
  private base_url = environment.api_url;
  private _refresh$ = new Subject<void>();

  constructor(private httpClient: HttpClient) { }
  get refresh$() {
    return this._refresh$;
  }

  addProduct(product, shop_id, cat_id):Observable<any> {
    return this.httpClient.post(this.base_url + 'add_product/' + shop_id + '/cat/' + cat_id, product )
    .pipe( tap(() => this._refresh$.next()));
  }
  getAllProducts():Observable<Product[]> {
    return this.httpClient.get<Product[]>(this.base_url + 'products');
  }
  getAllProductByFemale():Observable<Product[]> {
    return this.httpClient.get<Product[]>(this.base_url + 'products_ladies');
  }
  getAllProductByMale():Observable<Product[]> {
    return this.httpClient.get<Product[]>(this.base_url + 'products_gents');
  }
  getAllProductByChildren():Observable<Product[]> {
    return this.httpClient.get<Product[]>(this.base_url + 'products_children');
  }
  getProductById(id):Observable<Product> {
    return this.httpClient.get<Product>(this.base_url + 'product/' + id);
  }
  getAllProductsByRef(ref):Observable<Product[]> {
    return this.httpClient.get<Product[]>(this.base_url + 'products_by_ref/' + ref);
  }
  getProductByShopId(shopId: number):Observable<Product[]> {
    return this.httpClient.get<Product[]>(this.base_url + 'productsByShop/' + shopId);
  }
  editProduct(product, shop_id):Observable<any> {
    return this.httpClient.put(this.base_url +'update_product/' + shop_id, product)
    .pipe( tap(() => this._refresh$.next()));
  }
  deleteProduct(id):Observable<any> {
    return this.httpClient.delete(this.base_url + 'delete_product/' + `${id}`, {responseType: 'text' as 'json'})
    .pipe( tap(() => this._refresh$.next()));
  }
}
