import { Injectable } from "@angular/core";
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { environment } from "src/environments/environment";
import { Observable, BehaviorSubject, from } from "rxjs";

import { map } from "rxjs/operators";
import * as jwt_decode from "jwt-decode";
import { MsgService } from "./msg.service";
import { Router } from "@angular/router";
import { Storage } from "@ionic/storage";

@Injectable({
  providedIn: "root",
})
export class AuthenticationService {
  private base_url = environment.api_url;
  decoded_token: any = null;
  checkLoginSubject: BehaviorSubject<any> = new BehaviorSubject<any>(
    this.isLoggedIn()
  );
  checkLogIn$ = this.checkLoginSubject.asObservable();

  // checkAdminSubject: BehaviorSubject<any> = new BehaviorSubject<any>(this.isAdmin());
  // checkAdmin$ = this.checkAdminSubject.asObservable();

  // checkSuperAdminSubject: BehaviorSubject<any> = new BehaviorSubject<any>(this.isSuperAdmin());
  // checkSuperAdmin$ = this.checkSuperAdminSubject.asObservable();

  storedToken: any;

  constructor(
    private httpClient: HttpClient,
    private messageService: MsgService,
    private router: Router,
    private localStorage: Storage
  ) {}

  signIn(email: string, password: string): Observable<any> {
    return this.httpClient
      .post<any>(this.base_url + "authenticate", {
        email,
        password,
      })
      .pipe(
        map((res) => {
          if (res.jwt) {
            this.decoded_token = this.getDecodedAccessToken(res.jwt);
            this.localStorage.set("token", res.jwt);
            this.checkLoginSubject.next(this.isLoggedIn());
            // this.checkAdminSubject.next(this.isAdmin());
            // this.checkSuperAdminSubject.next(this.isSuperAdmin());
            return res;
          }
        })
      );
  }

  signOut() {
    this.localStorage.remove("token");
    this.localStorage.clear();
    this.checkLoginSubject.next(this.isLoggedIn());
    // this.checkAdminSubject.next(this.isAdmin());
    // this.checkSuperAdminSubject.next(this.isSuperAdmin());
  }
   isLoggedIn() {
     return this.localStorage.get("token");
  }
  getToken() {
  return  this.localStorage.get("token")
  .then(token => {
    if(token) return token;
    return 'not logged in';
  });
  }
  getDecodedAccessToken(token: string): any {
    try {
      return jwt_decode(token);
    } catch (Error) {
      return null;
    }
  }

  async isAdmin() {
    const token = await this.localStorage.get('token');
    if(this.isLoggedIn()) {
      const decodedToken = jwt_decode(token);
      if(decodedToken.scopes[0].authority == 'ROLE_ADMIN') {
        return true;
      }
      return false;
    }
  }

  async isSuperAdmin() {
    const token = await this.localStorage.get('token');
    if(this.isLoggedIn()) {
      const decodedToken = jwt_decode(token);
      if(decodedToken.scopes[0].authority == 'ROLE_SUPER_ADMIN') {
        return true;
      }
      return false;
    }
  }
 async getEmail() {
    await this.localStorage.get('token')
    .then(token =>{
      let email = this.getDecodedAccessToken(token).user_name;
      console.log('ttt :', email);
      return email;
    });
  }
  resetPasswordRequest(email: string): Observable<any> {
    return this.httpClient.post<any>(this.base_url + "reset_password", {
      email: email,
    });
  }
}
