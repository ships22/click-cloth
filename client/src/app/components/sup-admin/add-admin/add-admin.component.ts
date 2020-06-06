import { Component, OnInit, Injector } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';
import { Admin } from 'src/app/models/admin';

@Component({
  selector: 'app-add-admin',
  templateUrl: './add-admin.component.html',
  styleUrls: ['./add-admin.component.sass']
})
export class AddAdminComponent implements OnInit {
  
  private dialogRef = null;
  constructor(  
               private injector: Injector
              ) {
                this.dialogRef = this.injector.get(MatDialogRef, null);
               }

  ngOnInit(): void {
  }
  submit(newAdmin) {
    console.log('test add admin :', newAdmin);
    // this.dialogRef.close();
  }
}
