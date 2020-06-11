import { Component, OnInit, Injector } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';
import { MsgService } from 'src/app/services/msg.service';
import { ShopService } from 'src/app/services/shop.service';
import { AuthenticationService } from 'src/app/services/authentication.service';
import { AdminService } from 'src/app/services/admin.service';
import { take } from 'rxjs/operators';

@Component({
  selector: 'app-add-shop',
  templateUrl: './add-shop.component.html',
  styleUrls: ['./add-shop.component.sass']
})
export class AddShopComponent implements OnInit {

  private dialogRef = null;
  private adminId: number;
  constructor(
    private injector: Injector,
    private shopServie: ShopService,
    public messageService: MsgService,
    private authenticationService: AuthenticationService,
    private adminService: AdminService,
  ) {
    this.dialogRef = this.injector.get(MatDialogRef, null);
  }

  ngOnInit(): void {
    this.getAdminId();
  }


  submit(newShop) {
    
    if (
      newShop.name != "" &&
      newShop.phone!= "" &&
      newShop.email != "" &&
      newShop.address != ""
    ) {
      newShop.admin_id = this.adminId;
      this.shopServie.addShop(newShop).subscribe(
        (response) => console.log("test add response", response),
        (error) => {
          this.messageService.sendMessage(
            "Une erreur s'est produite. Veuillez réessayer"
          ),
            setTimeout(() => {
              this.dialogRef.close();
            }, 3000);
        },
        () => {
          this.dialogRef.close();
          console.log("finally called");
        }
      );
    } else {
      this.messageService.sendMessage("Tous les chams sont obligatoire!");
    }
    
  }

  getAdminId() {
    let adminEmail = this.authenticationService.getEmail();
    if (adminEmail) {
      this.adminService
        .getAdminByEmail(adminEmail)
        .pipe(take(1))
        .subscribe
          (response => {
            this.adminId = response.id, console.log(response);
          },
          error => this.messageService.sendMessage("Problème technique")
        );
    }
  }
}
