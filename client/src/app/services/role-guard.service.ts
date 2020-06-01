import { Injectable } from '@angular/core';
import { CanActivate, Router, ActivatedRouteSnapshot } from '@angular/router';
import { AuthenticationService } from './authentication.service';
import decode from 'jwt-decode';

@Injectable({
  providedIn: 'root'
})
export class RoleGuardService implements CanActivate {

  constructor(public authentication: AuthenticationService, public router: Router) { }

  canActivate(route: ActivatedRouteSnapshot): boolean {
    const expectedRole = route.data.expectedRole;
    const token = localStorage.getItem('token');
    if(this.authentication.isLoggedIn()) {
      const decodedToken = decode(token);
      if(decodedToken.scopes[0].authority != expectedRole) {
        this.router.navigate(['login']);
        return false;
      }
      return true;
    } 
    this.router.navigate(['login']);
    return false;

    // if(  || 
    //      ) {
    //       console.log('test here..');
          
    //       this.router.navigate(['login']);
    //       return false;
    //     }
    //     return true;
  }

}
