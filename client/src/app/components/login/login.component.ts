import { Component, OnInit } from "@angular/core";
import { AuthenticationService } from "../../services/authentication.service";
import { ClientService } from 'src/app/services/client.service';
import { MsgService } from 'src/app/services/msg.service';
import { Router } from '@angular/router';

@Component({
  selector: "app-login",
  templateUrl: "./login.component.html",
  styleUrls: ["./login.component.sass"],
})
export class LoginComponent implements OnInit {
  registering = false;
  forgotpassword = false;
  newUser: any = {};
  userToLogIn: any = {};
  userToReinitPass: any = {};
  message: any;
  loading = false;

  constructor(private authenticationService: AuthenticationService, 
              private clientService: ClientService,
              public messageService: MsgService,
              private router: Router) {}

  ngOnInit() {}

 signIn(formValues) {
    const email: string = this.userToLogIn.email;
    const password: string = this.userToLogIn.password;
    if(email && password) {
        this.authenticationService.signIn(email, password).subscribe(
      (res) => {
        this.messageService.sendMessage("vous êtes connecté");
        console.log(
          "res from login component : ",
          this.authenticationService.decoded_token
        );
        if(this.authenticationService.isSuperAdmin()){
          this.router.navigate(['super_admin']);
        }else if (this.authenticationService.isAdmin()) {
          this.router.navigate(['admin']);
        } else {
          this.router.navigate(['cart']);
        }
        this.messageService.sendMessage("vous êtes connecté");
      },
      (err) => {
        this.router.navigate(['']);
        this.messageService.sendMessage("e-mail ou mot de passe incorrect");
      });
    } 
   }

  register(formValues) {
    let new_password = formValues.new_password;
    let password_retyped = formValues.password_retyped;
    if(new_password === password_retyped) {
      this.clientService.registration(formValues, new_password)
      .subscribe(response => {
        if(response == 'e-mail déjà utilisé') {
          this.messageService.sendMessage('E-mail déjà utilisé');
        } else {
          this.messageService.sendMessage("Votre compet est crée avec success!");
          this.toggleRegistration();
        }
      }, 
      (error) => {
        this.messageService.sendMessage("Une erreur s'est produite");
      })
    } else {
      this.messageService.sendMessage('Les mots de passe ne correspondent pas')
    }
  }
  
  toggleRegistration() {
    if(this.forgotpassword) {
      this.forgotpassword = !this.forgotpassword;
    }
    this.registering = !this.registering;
    this.userToLogIn = {};
    this.newUser = {};
  }
  toggleReinit() {
    if(this.registering) {
      this.registering = !this.registering;
    }
    this.forgotpassword = !this.forgotpassword;
    this.userToReinitPass = {};
  }
  
  reinitPass(value) {
    this.authenticationService.resetPasswordRequest(value.email)
    .subscribe(response =>  {
      if(response.operationResult == 'USER DOES NOT EXIST') {
        this.messageService.sendMessage("Utilisateur n'est pas enregistré avec cette adresse mail!");
      } else {
        this.messageService.sendMessage( "Un lien de réinitialisation de mot de passe est envoyé à votre adresse mèl.");
        this.toggleReinit();
      }
    }, 
    (error) => {
      this.messageService.sendMessage("Une erreur s'est produite");
    })
  }
}
