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

  getAllAdmin():Observable<Admin[]> {
    return this.httpClient.get<Admin[]>(this.base_url + '/admins');
  }
}
