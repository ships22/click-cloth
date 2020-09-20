import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { AdminService } from 'src/app/services/admin.service';
import { Admin } from 'src/app/models/admin';
import { MsgService } from 'src/app/services/msg.service';

@Component({
  selector: 'app-edit-admin',
  templateUrl: './edit-admin.component.html',
  styleUrls: ['./edit-admin.component.sass']
})
export class EditAdminComponent implements OnInit {
  private id: number;
  admin: Admin = {
    id: null,
    first_name: null,
    last_name: null,
    email: null,
    address: null,
    shops: null
  };

  constructor(  private router: Router,
                private adminService: AdminService,
                private massageService: MsgService,
                private activatedRoute: ActivatedRoute ) { }

  ngOnInit(): void {
    this.activatedRoute.paramMap
    .subscribe(parametarMap => {
      this.id = +parametarMap.get('id');
      this.getAdminById(this.id);
    })
  }

  getAdminById(id) {
    this.adminService.getAdminById(id)
    .subscribe(response => this.admin = response)
  }

  submit(admin) {
    console.log(admin);
    admin.id = this.id;
    this.adminService.editAdmin(admin.id, admin)
    .subscribe(
        response => this.router.navigate(['/super_admin']),
        error => this.massageService.sendMessage('Une erreur s\'est produite. Veuillez réessayer'));
  }

}
