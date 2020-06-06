import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';
import { AuthenticationService } from './authentication.service';

@Injectable({
  providedIn: 'root'
})
export class AuthGuardService implements CanActivate {

  constructor(public authentication: AuthenticationService, public router: Router) { }

  canActivate(): boolean {
    if(!this.authentication.isLoggedIn()) {
      console.log('test auth guard');
      
      this.router.navigate(['login']);
      return false;
    }
    return true;
  }
}
