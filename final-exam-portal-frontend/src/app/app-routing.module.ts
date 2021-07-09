import { HomeComponent } from './pages/home/home.component';
import { LoginComponent } from './pages/login/login.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SignupComponent } from './pages/signup/signup.component';
import { ModeratorDashboardComponent } from './pages/moderator/moderator-dashboard/moderator-dashboard.component';
import { UserDashboardComponent } from './pages/user/user-dashboard/user-dashboard.component';
import { AdminDashboardComponent } from './pages/admin/admin-dashboard/admin-dashboard.component';
import { AdminGuard } from './services/admin.guard';
import { ModeratorGuard } from './services/moderator.guard';
import { UserGuard } from './services/user.guard';
import { SignupModeratorComponent } from './pages/signup-moderator/signup-moderator.component';
import { ProfileComponent } from './pages/profile/profile.component';
import { WelcomeComponent } from './pages/moderator/welcome/welcome.component';

const routes: Routes = [
  {
    path:'',
    component:HomeComponent,
    pathMatch:'full'
  },
  {
    path:'signup',
    component:SignupComponent,
    pathMatch:'full'
  },
  {
      path:'moderator-signup',
      component:SignupModeratorComponent,
      pathMatch:'full'
  },
  {
    path:'login',
    component:LoginComponent,
    pathMatch:'full'
  },
  {
    path:'moderator-dashboard',
    component:ModeratorDashboardComponent,
    canActivate:[ModeratorGuard],
    children:[
      {
        path:'',
        component:WelcomeComponent
      },
      {
        path:'moderator-profile',
        component:ProfileComponent
      }
    ]
  },
  {
    path:'user-dashboard',
    component:UserDashboardComponent,
    pathMatch:'full',
    canActivate:[UserGuard]
  },
  {
    path:'admin-dashboard',
    component:AdminDashboardComponent,
    pathMatch:'full',
    canActivate:[AdminGuard]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
