import { NgModule } from "@angular/core";
import { Routes, RouterModule } from "@angular/router";
import { LoginComponent } from "./components/login/login.component";
import { HomeComponent } from './components/home/home.component';
import { TestComponent } from './components/test/test.component';
import { RoleGuardService as Guard} from './services/role-guard.service';

const routes: Routes = [
  { path: "", component: HomeComponent },
  { path: "login", component: LoginComponent },
  { 
    path: "test", component: TestComponent,
    canActivate: [Guard],
    data: {
      expectedRole: 'ROLE_CLIENT'
    }
  },
  { path: '**', redirectTo: ''}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
