import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Subject, Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Categories } from '../models/products/categories';
import { tap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class CategoryService {

  private base_url = environment.api_url;
  private _refresh$ = new Subject<void>();

  constructor(private httpClient: HttpClient) { }
  get refresh$() {
    return this._refresh$;
  }

  addCategory(category: any):Observable<any> {
    return this.httpClient.post(this.base_url + 'add_category/', category)
    .pipe( tap(() => this._refresh$.next()));
  }
  getAllCategory():Observable<Categories[]> {
    return this.httpClient.get<Categories[]>(this.base_url + 'categories');
  }
  deleteCategory(id):Observable<any> {
    return this.httpClient.delete(this.base_url + 'delete_category/' + `${id}`, {responseType: 'text' as 'json'})
    .pipe( tap(() => this._refresh$.next()));
  }
}
