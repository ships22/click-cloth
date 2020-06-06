import { Component, OnInit, ViewChild, Injector } from '@angular/core';
import { AdminService } from 'src/app/services/admin.service';
import { Admin } from 'src/app/models/admin';
import { MsgService } from 'src/app/services/msg.service';
import { MatTableDataSource } from '@angular/material/table';
import { MatSort } from '@angular/material/sort';
import { MatPaginator } from '@angular/material/paginator';
import { MatDialog, MatDialogModule, MatDialogRef } from '@angular/material/dialog';
import { AddAdminComponent } from './add-admin/add-admin.component';
import { Router } from '@angular/router';

@Component({
  selector: 'app-sup-admin',
  templateUrl: './sup-admin.component.html',
  styleUrls: ['./sup-admin.component.sass']
})
export class SupAdminComponent implements OnInit {

  adminListInIt: Admin[];
  adminMatList: MatTableDataSource<Admin>;
  searchKey: string;
  columns: string[] = ['id', 'first_name', 'email', 'address', 'options'];

  @ViewChild(MatSort, {static: true}) sort: MatSort;
  @ViewChild(MatPaginator) paginator: MatPaginator;

  constructor( private adminService: AdminService, 
               private router: Router,
               public messageService: MsgService,
               private dialog: MatDialog,
               private dialogRef: MatDialogRef<AddAdminComponent>
               ) {
                
                }

  ngOnInit(): void {
    setTimeout(() => {
      this.getAllAdmins();
    }, 1000);
  }

  getAllAdmins() {
    this.adminService.getAllAdmin()
    .subscribe(
      response => {
        this.adminListInIt = response; 
        this.adminMatList = new MatTableDataSource(this.adminListInIt);
        this.adminMatList.sort = this.sort;
        this.adminMatList.paginator = this.paginator;
        console.log(response);
      },
      error => this.messageService.sendMessage('Problème technique')
      );
  }
  filter() {
    this.adminMatList.filter = this.searchKey.trim().toLocaleLowerCase();
  }

  onCreate() {
    this.dialog.open(AddAdminComponent);
  }
  onEdit(admin) {
   this.router.navigate(['/edit_admin', admin.id]); 
  }
}
