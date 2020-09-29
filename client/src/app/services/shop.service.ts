import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { Subject, Observable } from 'rxjs';
import { tap } from 'rxjs/operators';
import { Shop } from '../models/shop';

@Injectable({
  providedIn: 'root'
})
export class ShopService {
  private base_url = environment.api_url;
  private _refresh$ = new Subject<void>();
  private token;
  constructor(private httpClient: HttpClient) {
    this.token = 'Bearer ' + this.getToken();
   }

  get refresh$() {
    return this._refresh$;
  }
  getToken():string {
    return localStorage.getItem('token');
  }
  addShop(shop):Observable<any> {
    const headers = new HttpHeaders().set("Authorization", this.token );
    return this.httpClient.post(this.base_url + 'add_shop/' + shop.admin_id + '/shop/', shop, {headers})
    .pipe( tap(() => this._refresh$.next()));
  }
  getShopByAdmin(admin_id):Observable<Shop> {
    const headers = new HttpHeaders().set("Authorization", this.token );
    return this.httpClient.get<Shop>(this.base_url + 'shop_by_admin_id/' + admin_id, {headers});
  }
}
