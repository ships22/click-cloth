import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, Subject } from 'rxjs';
import { Admin } from '../models/admin';
import { tap, share } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class AdminService {

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

  addAdmin(admin):Observable<any> {
    const headers = new HttpHeaders().set("Authorization", this.token );
    return this.httpClient.post(this.base_url + 'add_admin/', admin, {headers})
    .pipe( tap(() => this._refresh$.next()));
  }
  getAllAdmin():Observable<Admin[]> {
    const headers = new HttpHeaders().set("Authorization", this.token );
    return this.httpClient.get<Admin[]>(this.base_url + 'admins', {headers});
  }
  getAdminById(id):Observable<Admin> {
    const headers = new HttpHeaders().set("Authorization", this.token );
    return this.httpClient.get<Admin>(this.base_url + 'admin_by_id/' + id, {headers});
  }
  getAdminByEmail(email: string):Observable<Admin> {
    const headers = new HttpHeaders().set("Authorization", this.token );
    return this.httpClient.get<Admin>(this.base_url + 'admin_by_email/' + email, {headers})
    .pipe( share());
  }
  editAdmin(id, admin):Observable<any> {
    const headers = new HttpHeaders().set("Authorization", this.token );
    return this.httpClient.put(this.base_url +'update_admin/' + `${id}`, admin, {headers});
  }
  deleteAdmin(id):Observable<any> {
    const headers = new HttpHeaders().set("Authorization", this.token );
    return this.httpClient.delete(this.base_url + 'delete_admin/' + `${id}`,{headers, responseType: 'text' as 'json'})
    .pipe( tap(() => this._refresh$.next()));
  }
}
