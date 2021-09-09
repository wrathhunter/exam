import { UserwelcomeComponent } from './pages/user/userwelcome/userwelcome.component';
import { StartComponent } from './pages/user/start/start.component';
import { ApeearquizComponent } from './pages/user/apeearquiz/apeearquiz.component';
import { AddquestionsComponent } from './pages/moderator/addquestions/addquestions.component';
import { ViewquestionsComponent } from './pages/moderator/viewquestions/viewquestions.component';
import { AddquizsComponent } from './pages/moderator/addquizs/addquizs.component';
import { ViewquizsComponent } from './pages/moderator/viewquizs/viewquizs.component';
import { AddcategoriesComponent } from './pages/moderator/addcategories/addcategories.component';
import { ViewcategoriesComponent } from './pages/moderator/viewcategories/viewcategories.component';
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
import { ViewExamComponent } from './pages/moderator/view-exam/view-exam.component';
import { AddExamComponent } from './pages/moderator/add-exam/add-exam.component';

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
      },
      {
        path:'exams',
        component:ViewExamComponent
      },
      {
        path:'addexam',
        component:AddExamComponent
      },
      {
        path:'categories/:examName',
        component:ViewcategoriesComponent
      },
      {
        path:'addcategory/:examName',
        component:AddcategoriesComponent
      },
      {
        path:'quiz/:categoryName/:examName',
        component:ViewquizsComponent
      },
      {
        path:'addquiz/:categoryName/:examName',
        component:AddquizsComponent
      },
      {
        path:'viewquestion/:quizname/:categoryName/:examName',
        component:ViewquestionsComponent
      },
      {
        path:'addquestion/:quizname/:categoryName/:examName',
        component:AddquestionsComponent
      },
    ]
  },
  {
    path:'user-dashboard',
    component:UserDashboardComponent,
    canActivate:[UserGuard],
    children:[
      {
        path:'',
        component:UserwelcomeComponent
      },
      {
        path:'appearexam',
        component:ApeearquizComponent
      },

    ]
  },
  {
    path:'admin-dashboard',
    component:AdminDashboardComponent,
    pathMatch:'full',
    canActivate:[AdminGuard]
  },
  {
    path:'start/:quizid',
    component:StartComponent
    }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
