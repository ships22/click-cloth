import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from 'src/app/services/authentication.service';
import { RoleGuardService } from 'src/app/services/role-guard.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.sass']
})
export class HeaderComponent implements OnInit {
  
  isLoggedIn$;
  isAdmin$;

  constructor(public authentication: AuthenticationService, public roleGuardService: RoleGuardService) { }

  ngOnInit(): void {
    this.isLoggedIn$ = this.authentication.checkLogIn$;
    this.isAdmin$ = this.authentication.checkAdmin$;
  }
  logout() {
    this.authentication.signOut();
  }

}
