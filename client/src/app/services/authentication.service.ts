import { Injectable } from "@angular/core";
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { environment } from "src/environments/environment";
import { Observable, BehaviorSubject } from "rxjs";
import { map } from "rxjs/operators";
import * as jwt_decode from "jwt-decode";
import { MsgService } from './msg.service';
import { Router } from '@angular/router';

@Injectable({
  providedIn: "root",
})
export class AuthenticationService {
  private base_url = environment.api_url;
  decoded_token: any = null;
  checkLoginSubject: BehaviorSubject<any> = new BehaviorSubject<any>(this.isLoggedIn());
  checkLogIn$ = this.checkLoginSubject.asObservable();

  constructor(
    private httpClient: HttpClient,
    private messageService: MsgService,
    private router: Router
    ) {}

  signIn(email: string, password: string): Observable<any> {
    return this.httpClient
      .post<any>(this.base_url + "/authenticate", {
        email,
        password
      })
      .pipe(
        map((res) => {
          if (res.jwt) {
            this.decoded_token = this.getDecodedAccessToken(res.jwt);
            localStorage.setItem("token", res.jwt);       
            this.checkLoginSubject.next(this.isLoggedIn());
            return res;
          }
        })
      );
  }

  signOut() {
    localStorage.removeItem('token');
    localStorage.clear();
    this.checkLoginSubject.next(this.isLoggedIn());
  }
  isLoggedIn():boolean {
    if(this.getToken()) {
      console.log('isLoggedIn called...');
      return true;
    }
    return false;
  }
  getToken():string {
    return localStorage.getItem('token');
  }
  getDecodedAccessToken(token: string): any {
    try {
      return jwt_decode(token);
    } catch (Error) {
      return null;
    }
  }
}
