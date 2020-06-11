import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { Subject, Observable } from 'rxjs';
import { tap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class ShopService {
  private base_url = environment.api_url;
  private _refresh$ = new Subject<void>();

  constructor(private httpClient: HttpClient) { }

  get refresh$() {
    return this._refresh$;
  }
  addShop(shop):Observable<any> {
    return this.httpClient.post(this.base_url + 'add_shop/' + shop.admin_id, shop)
    .pipe( tap(() => this._refresh$.next()));
  }
}
