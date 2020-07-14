import { NgModule } from "@angular/core";
import { PreloadAllModules, RouterModule, Routes } from "@angular/router";
import { AllProductsComponent } from "./components/all-products/all-products.component";
import { AllProductsPage } from './pages/all-products/all-products.page';

const routes: Routes = [
  {
    path: "",
    loadChildren: () =>
      import("./home/home.module").then((m) => m.HomePageModule),
  },
  // {
  //   path: "landing",
  //   loadChildren: () =>
  //     import("./pages/landing/landing.module").then((m) => m.LandingPageModule),
  // },
  // {
  //   path: "account",
  //   loadChildren: () =>
  //     import("./pages/account/account.module").then((m) => m.AccountPageModule),
  // },
  // {
  //   path: "allProducts",
  //   component: AllProductsPage,
  // },
  // {
  //   path: 'all-products',
  //   loadChildren: () => import('./pages/all-products/all-products.module').then( m => m.AllProductsPageModule)
  // },
];


// const routes: Routes = [
//   {
//     path: '',
    
//     children: [
//       {
//         path: 'cart',
//         children: [
//           {
//             path: '',
//             loadChildren: () => import('./pages/cart/cart.module').then( m => m.CartPageModule)
//           }
//         ]
//       },
//       {
//         path: 'landing',
//         children: [
//           {
//             path: '',
//             loadChildren: () => import('./pages/landing/landing.module').then( m => m.LandingPageModule)
//           }
//         ]
//       },
//       {
//         path: 'account',
//         children: [
//           {
//             path: '',
//             loadChildren: () => import('./pages/account/account.module').then( m => m.AccountPageModule)
//           }
//         ]
//       },
//       {
//             path: 'all-products',
//             children: [
//               {
//                 path: '',
//                 loadChildren: () => import('./pages/all-products/all-products.module').then( m => m.AllProductsPageModule)
//               }
//             ]
//       },
//       {
//         path: '', 
//         redirectTo: 'landing',
//         pathMatch: 'full'
//       }
//     ]
//   },
//   {
//     path: '', 
//     redirectTo: 'landing',
//     pathMatch: 'full'
//   }
// ];

@NgModule({
  imports: [
    RouterModule.forRoot(routes, { preloadingStrategy: PreloadAllModules }),
  ],
  exports: [RouterModule],
})
export class AppRoutingModule {}
