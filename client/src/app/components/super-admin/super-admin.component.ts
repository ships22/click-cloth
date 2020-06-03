import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTable } from '@angular/material/table';
import { SuperAdminDataSource } from './super-admin-datasource';
import { Admin } from 'src/app/models/admin';
import { AdminService } from 'src/app/services/admin.service';

@Component({
  selector: 'app-super-admin',
  templateUrl: './super-admin.component.html',
  styleUrls: ['./super-admin.component.css']
})
export class SuperAdminComponent implements AfterViewInit, OnInit {
  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;
  @ViewChild(MatTable) table: MatTable<Admin>;
  dataSource: SuperAdminDataSource;
  
  searchKey: string;
  /** Columns displayed in the table. Columns IDs can be added, removed, or reordered. */
  displayedColumns = ['id', 'name', 'option'];

  constructor(private adminService: AdminService){}
  // DATA= [
  //   {id: 1, name: 'test'},
  //   {id: 2, name: 'Helium'}
  // ];





  ngOnInit() {
    this.dataSource = new SuperAdminDataSource();
    this.getAll();
  }
  getAll() {
    this.adminService.getAllAdmin()
    .subscribe(res => {
      console.log('tes:', res);
      this.dataSource.data = res;
    })
  }

  ngAfterViewInit() {
    this.dataSource.sort = this.sort;
    this.dataSource.paginator = this.paginator;
    this.table.dataSource = this.dataSource;
  }

  clearSearch() {
    this.searchKey = "";
  }
  // filter() {
  //   this.dataSource.filter = this.searchKey.trim().toLocaleLowerCase()
  // }
}
