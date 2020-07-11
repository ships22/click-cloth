import { Component } from '@angular/core';
import { NavController } from '@ionic/angular';

@Component({
  selector: 'app-home',
  templateUrl: 'home.page.html',
  styleUrls: ['home.page.scss'],
})
export class HomePage {

  splash = true;
  tabBarElement: any;

  constructor(public navCtrl: NavController) {
    //this.tabBarElement = document.querySelector('.tabbar');
    //this.tabBarElement.style.display = 'none';
    setTimeout(() => {
      this.splash = false;
      //this.tabBarElement.style.display = 'flex';
    }, 4000);
  }

  

}
