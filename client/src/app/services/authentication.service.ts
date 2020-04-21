import { Injectable } from "@angular/core";
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { environment } from "src/environments/environment";
import { Observable } from "rxjs";
import { map } from "rxjs/operators";
import * as jwt_decode from "jwt-decode";

@Injectable({
  providedIn: "root",
})
export class AuthenticationService {
  private base_url = environment.api_url;
  decoded_token: any = null;

  constructor(private httpClient: HttpClient) {}

  signIn(email: string, password: string): Observable<any> {
    return this.httpClient
      .post<any>(this.base_url + "/authenticate", {
        email,
        password,
      })
      .pipe(
        map((res) => {
          if (res.jwt) {
            this.decoded_token = this.getDecodedAccessToken(res.jwt);
            localStorage.setItem("token", res.jwt);
            return res;
          }
        })
      );
  }
  getDecodedAccessToken(token: string): any {
    try {
      return jwt_decode(token);
    } catch (Error) {
      return null;
    }
  }
}
