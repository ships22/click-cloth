import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { HttpClient } from '@angular/common/http';
import { Client } from '../models/client';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ClientService {

  private base_url = environment.api_url;

  constructor(private httpClient: HttpClient) {}

  registration(newClient: Client, password: string): Observable<any> {
  return this.httpClient.post<Client>(this.base_url + '/add_client/' + password, newClient, {responseType: 'text' as 'json'});
  }  
}
