import { Injectable } from "@angular/core";
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { environment } from "src/environments/environment";
import { Observable, BehaviorSubject } from "rxjs";
import { map } from "rxjs/operators";
import * as jwt_decode from "jwt-decode";
import { MsgService } from './msg.service';
import { Router } from '@angular/router';
import { Storage } from '@ionic/storage';

@Injectable({
  providedIn: "root",
})
export class AuthenticationService {
  private base_url = environment.api_url;
  decoded_token: any = null;
  checkLoginSubject: BehaviorSubject<any> = new BehaviorSubject<any>(this.isLoggedIn());
  checkLogIn$ = this.checkLoginSubject.asObservable();

  checkAdminSubject: BehaviorSubject<any> = new BehaviorSubject<any>(this.isAdmin());
  checkAdmin$ = this.checkAdminSubject.asObservable();

  checkSuperAdminSubject: BehaviorSubject<any> = new BehaviorSubject<any>(this.isSuperAdmin());
  checkSuperAdmin$ = this.checkSuperAdminSubject.asObservable();

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
        password
      })
      .pipe(
        map((res) => {
          if (res.jwt) {
            this.decoded_token = this.getDecodedAccessToken(res.jwt);
            this.localStorage.set("token", res.jwt);       
            this.checkLoginSubject.next(this.isLoggedIn());
            this.checkAdminSubject.next(this.isAdmin());
            this.checkSuperAdminSubject.next(this.isSuperAdmin());
            return res;
          }
        })
      );
  }

  signOut() {
    this.localStorage.remove('token');
    this.localStorage.clear();
    this.checkLoginSubject.next(this.isLoggedIn());
    this.checkAdminSubject.next(this.isAdmin());
    this.checkSuperAdminSubject.next(this.isSuperAdmin());
  }

  isLoggedIn():boolean {
    if(this.getToken()) {
      return true;
    }
    return false;
  }
  getToken():string {
    let token;
    this.localStorage.get('token').then(value => {
      token = JSON.stringify(value);
    });
    return token;
  }
  getDecodedAccessToken(token: string): any {
    try {
      return jwt_decode(token);
    } catch (Error) {
      return null;
    }
  }

  isAdmin() {
    const token = localStorage.getItem('token');
    if(this.isLoggedIn()) {
      const decodedToken = jwt_decode(token);
      if(decodedToken.scopes[0].authority == 'ROLE_ADMIN') {
        return true;
      }
      return false;
    } 
  }

  isSuperAdmin() {
    const token = localStorage.getItem('token');
    if(this.isLoggedIn()) {
      const decodedToken = jwt_decode(token);
      if(decodedToken.scopes[0].authority == 'ROLE_SUPER_ADMIN') {
        return true;
      }
      return false;
    } 
  }
  getEmail(): string {
    const token = localStorage.getItem('token');
    if(this.isLoggedIn()) {
      return this.getDecodedAccessToken(token).user_name;
    }
  }
  resetPasswordRequest(email: string):Observable<any> {
    return this.httpClient.post<any>(this.base_url + "reset_password", {"email" : email});
  }
}
