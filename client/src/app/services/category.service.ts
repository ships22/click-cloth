import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Subject, Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Categories } from '../models/products/categories';
import { tap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class CategoryService {

  private base_url = environment.api_url;
  private _refresh$ = new Subject<void>();
  private token;
  constructor(private httpClient: HttpClient) {
    this.token = 'Bearer ' + this.getToken()
   }
  get refresh$() {
    return this._refresh$;
  }
  getToken():string {
    return localStorage.getItem('token');
  }

  addCategory(category: any):Observable<any> {
    const headers = new HttpHeaders().set("Authorization", this.token );
    return this.httpClient.post(this.base_url + 'add_category/', category, {headers})
    .pipe( tap(() => this._refresh$.next()));
  }
  getAllCategory():Observable<Categories[]> {
    return this.httpClient.get<Categories[]>(this.base_url + 'categories');
  }
  deleteCategory(id):Observable<any> {
    const headers = new HttpHeaders().set("Authorization", this.token );
    return this.httpClient.delete(this.base_url + 'delete_category/' + `${id}`, {headers, responseType: 'text' as 'json'})
    .pipe( tap(() => this._refresh$.next()));
  }
}
