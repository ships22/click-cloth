import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Admin } from '../models/admin';

@Injectable({
  providedIn: 'root'
})
export class AdminService {

  private base_url = environment.api_url;
  
  
  constructor(private httpClient: HttpClient) {}

  addAdmin(admin):Observable<any> {
    return this.httpClient.post(this.base_url, admin);
  }
  getAllAdmin():Observable<Admin[]> {
    return this.httpClient.get<Admin[]>(this.base_url + 'admins');
  }
  getAdminById(id):Observable<Admin> {
    return this.httpClient.get<Admin>(this.base_url + 'admin_by_id/' + id)
  }
  editAdmin(id, admin):Observable<any> {
    return this.httpClient.put(this.base_url +'update_admin/' + `${id}`, admin);
  }
  deleteAdmin(id):Observable<any> {
    return this.httpClient.delete(this.base_url + `${id}`);
  }
}
