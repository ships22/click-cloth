import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from 'src/app/services/authentication.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.sass']
})
export class HeaderComponent implements OnInit {

  constructor(public authentication: AuthenticationService) { }
  isLoggedIn$;
  ngOnInit(): void {
    this.isLoggedIn$ = this.authentication.checkLogIn$;
  }
  logout() {
    this.authentication.signOut();
  }

}
