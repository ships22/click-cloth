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
  async getToken() {
    return await this.localStorage.get("token");
  }
  getDecodedAccessToken(token: string): any {
    try {
      return jwt_decode(token);
    } catch (Error) {
      return null;
    }
  }

  isClient() {
    return this.getToken()
    .then(token => {
      let role = this.getDecodedAccessToken(token).scopes[0].authority;
      if(role == 'ROLE_CLIENT') return true;
      return false;
    });
    
      // if(this.decoded_token.scopes[0].authority == 'ROLE_CLIENT') {
      //   return true;
      // }
      // return false;
    }
  isAdmin() {
      if(this.decoded_token.scopes[0].authority == 'ROLE_ADMIN') {
        return true;
      }
      return false;
    }
  

  isSuperAdmin() {
      if(this.decoded_token.scopes[0].authority == 'ROLE_SUPER_ADMIN') {
        return true;
      }
      return false;
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
