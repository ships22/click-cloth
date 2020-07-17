import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomePage } from './home.page';
import { AllProductsComponent } from '../components/all-products/all-products.component';
import { ProductSelectComponent } from '../components/product-select/product-select.component';
import { CartComponent } from '../components/cart/cart.component';
import { LoginComponent } from '../components/login/login.component';

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
            //loadChildren: () => import('../pages/cart/cart.module').then( m => m.CartPageModule)
            component: CartComponent
          }
        ]
      },
      {
        path: 'account',
        children: [
          {
            path: '',
            //loadChildren: () => import('../pages/cart/cart.module').then( m => m.CartPageModule)
            component: LoginComponent
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
        children: [
          {
            path: 'product_select/:productRef',
            component: ProductSelectComponent
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
