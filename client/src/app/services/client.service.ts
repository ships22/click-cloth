import { Injectable } from "@angular/core";
import { environment } from "src/environments/environment";
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Client } from "../models/client";
import { Observable } from "rxjs";

@Injectable({
  providedIn: "root",
})
export class ClientService {
  private base_url = environment.api_url;
  private token;

  constructor(private httpClient: HttpClient) {

    this.token = 'Bearer ' + this.getToken();
  }

  getToken():string {
    return localStorage.getItem('token');
  }

  registration(newClient: Client, password: string): Observable<any> {
    return this.httpClient.post<Client>(
      this.base_url + "add_client/" + password,
      newClient,
      { responseType: "text" as "json" }
    );
  }

  getClientByEmail(email: string) {
    const headers = new HttpHeaders().set("Authorization", this.token );
    return this.httpClient.get<Client>(this.base_url + "client_by_email/" + email, {headers});
  }
}
