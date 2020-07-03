import { Component, OnInit, Injector } from "@angular/core";
import { MatDialogRef } from "@angular/material/dialog";
import { Admin } from "src/app/models/admin";
import { AdminService } from "src/app/services/admin.service";
import { MsgService } from "src/app/services/msg.service";

@Component({
  selector: "app-add-admin",
  templateUrl: "./add-admin.component.html",
  styleUrls: ["./add-admin.component.sass"],
})
export class AddAdminComponent implements OnInit {
  private dialogRef = null;
  constructor(
    private injector: Injector,
    private adminService: AdminService,
    public massegeService: MsgService
  ) {
    this.dialogRef = this.injector.get(MatDialogRef, null);
  }

  ngOnInit(): void {}
  submit(newAdmin) {
    if (
      newAdmin.first_name != "" &&
      newAdmin.lasst_name != "" &&
      newAdmin.email != "" &&
      newAdmin.address != ""
    ) {
      this.adminService.addAdmin(newAdmin).subscribe(
        (response) => console.log("test add response", response),
        (error) => {
          this.massegeService.sendMessage(
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
      this.massegeService.sendMessage("Tous les chams sont obligatoire!");
    }
  }
}
