import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomePage } from './home.page';
import { AllProductsComponent } from '../components/all-products/all-products.component';

const routes: Routes = [
  {
    path: 'tabs',
    component: HomePage,
    children: [
      {
        path: 'cart',
        children: [
          {
            path: '',
            loadChildren: () => import('../pages/cart/cart.module').then( m => m.CartPageModule)
          }
        ]
      },
      {
        path: 'landing',
        children: [
          {
            path: '',
            loadChildren: () => import('../pages/landing/landing.module').then( m => m.LandingPageModule)
          }
        ]
      },
      {
        path: 'account',
        children: [
          {
            path: '',
            loadChildren: () => import('../pages/account/account.module').then( m => m.AccountPageModule)
          }
        ]
      },
      {
        path: 'all-products',
        children: [
          {
            path: '',
            // loadChildren: () => import('../components/all-products/all-products.component').then( m => m.AllProductsComponent)
            component: AllProductsComponent
          }
        ]
      },
      {
        path: '', 
        redirectTo: '/tabs/landing',
        pathMatch: 'full'
      }
    ]
  },
  {
    path: '', 
    redirectTo: '/tabs/landing',
    pathMatch: 'full'
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class HomePageRoutingModule {}
