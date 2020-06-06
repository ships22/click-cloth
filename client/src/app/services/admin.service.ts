import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { HttpClient } from '@angular/common/http';
import { Observable, Subject } from 'rxjs';
import { Admin } from '../models/admin';
import { tap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class AdminService {

  private base_url = environment.api_url;
  private _refresh$ = new Subject<void>();
  
  constructor(private httpClient: HttpClient) {}

  get refresh$() {
    return this._refresh$;
  }

  addAdmin(admin):Observable<any> {
    return this.httpClient.post(this.base_url + 'add_admin/', admin)
    .pipe( tap(() => this._refresh$.next()));
  }
  getAllAdmin():Observable<Admin[]> {
    return this.httpClient.get<Admin[]>(this.base_url + 'admins');
  }
  getAdminById(id):Observable<Admin> {
    return this.httpClient.get<Admin>(this.base_url + 'admin_by_id/' + id);
  }
  getAdminByEmail(email: string):Observable<Admin> {
    return this.httpClient.get<Admin>(this.base_url + 'admin_by_email/' + email);
  }
  editAdmin(id, admin):Observable<any> {
    return this.httpClient.put(this.base_url +'update_admin/' + `${id}`, admin);
  }
  deleteAdmin(id):Observable<any> {
    return this.httpClient.delete(this.base_url + 'delete_admin/' + `${id}`, {responseType: 'text' as 'json'})
    .pipe( tap(() => this._refresh$.next()));
  }
}
