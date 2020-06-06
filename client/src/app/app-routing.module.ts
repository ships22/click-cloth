import { NgModule } from "@angular/core";
import { Routes, RouterModule } from "@angular/router";
import { LoginComponent } from "./components/login/login.component";
import { HomeComponent } from './components/home/home.component';
import { TestComponent } from './components/test/test.component';
import { RoleGuardService as Guard} from './services/role-guard.service';
import { SupAdminComponent } from './components/sup-admin/sup-admin.component';
import { EditAdminComponent } from './components/sup-admin/edit-admin/edit-admin.component';

import { AdminComponent } from './components/admin/admin.component';

const routes: Routes = [
  // { path: "", component: HomeComponent },
  { path: "", component: AdminComponent },
  { path: "login", component: LoginComponent },
  { path: "edit_admin/:id", component: EditAdminComponent },
  { 
    path: "test", component: TestComponent,
    canActivate: [Guard],
    data: {
      expectedRole: 'ROLE_CLIENT'
    }
  },
  { 
    path: "super_admin", component: SupAdminComponent,
    canActivate: [Guard],
    data: {
      expectedRole: 'ROLE_SUPER_ADMIN'
    }
  },
  { path: '**', redirectTo: ''}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
