import { Injectable } from "@angular/core";
import { environment } from "src/environments/environment";
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Client } from "../models/client";
import { Observable } from "rxjs";
import { Storage } from "@ionic/storage";

@Injectable({
  providedIn: "root",
})
export class ClientService {
  private base_url = environment.api_url;
  private token;

  constructor(private httpClient: HttpClient, private localStorage: Storage) {
    this.token = 'Bearer ' + this.getToken();
  }
  async getToken() {
    return await this.localStorage.get("token");
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
