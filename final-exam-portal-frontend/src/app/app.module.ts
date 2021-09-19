import { UserDashboardComponent } from './pages/user/user-dashboard/user-dashboard.component';
import { AdminDashboardComponent } from './pages/admin/admin-dashboard/admin-dashboard.component';
import { ModeratorDashboardComponent } from './pages/moderator/moderator-dashboard/moderator-dashboard.component';
import { NgModule, Component } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FooterComponent } from './components/footer/footer.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { LoginComponent } from './pages/login/login.component';
import { SignupComponent } from './pages/signup/signup.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatButtonModule} from '@angular/material/button';
import {MatCardModule} from '@angular/material/card';
import {MatGridListModule} from '@angular/material/grid-list';
import { AngularMaterialModule } from './angular-material.module';
import { CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { FlexLayoutModule } from '@angular/flex-layout';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import {MatSnackBarModule} from '@angular/material/snack-bar';
import { HomeComponent } from './pages/home/home.component';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatIconModule} from '@angular/material/icon';
import { AuthInterceptorProviders } from './services/auth.interceptor';
import { SignupModeratorComponent } from './pages/signup-moderator/signup-moderator.component';
import { SidebarComponent } from './pages/moderator/sidebar/sidebar.component';
import { ProfileComponent } from './pages/profile/profile.component';
import {MatListModule} from '@angular/material/list';
import { ViewExamComponent } from './pages/moderator/view-exam/view-exam.component';
import { AddExamComponent } from './pages/moderator/add-exam/add-exam.component';
import { ViewcategoriesComponent } from './pages/moderator/viewcategories/viewcategories.component';
import { AddcategoriesComponent } from './pages/moderator/addcategories/addcategories.component';
import { ViewquizsComponent } from './pages/moderator/viewquizs/viewquizs.component';
import { AddquizsComponent } from './pages/moderator/addquizs/addquizs.component';
import { AddquestionsComponent } from './pages/moderator/addquestions/addquestions.component';
import { ViewquestionsComponent } from './pages/moderator/viewquestions/viewquestions.component';
import { CKEditorModule } from '@ckeditor/ckeditor5-angular';
import { ApeearquizComponent } from './pages/user/apeearquiz/apeearquiz.component';
import { StartComponent } from './pages/user/start/start.component';
import { UsersidebarComponent } from './pages/user/usersidebar/usersidebar.component';
import { UserwelcomeComponent } from './pages/user/userwelcome/userwelcome.component';
import {ClipboardModule} from '@angular/cdk/clipboard';




@NgModule({
  declarations: [
    AppComponent,
    FooterComponent,
    NavbarComponent,
    LoginComponent,
    SignupComponent,
    HomeComponent,
    SignupModeratorComponent,
    ModeratorDashboardComponent,
    AdminDashboardComponent,
    SidebarComponent,
    ProfileComponent,
    ViewExamComponent,
    AddExamComponent,
    ViewcategoriesComponent,
    AddcategoriesComponent,
    ViewquizsComponent,
    AddquizsComponent,
    AddquestionsComponent,
    ViewquestionsComponent,
    ApeearquizComponent,
    UserDashboardComponent,
    StartComponent,
    UsersidebarComponent,
    UserwelcomeComponent



  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatButtonModule,
    MatCardModule,
    MatGridListModule,
    AngularMaterialModule,
    FlexLayoutModule,
    ReactiveFormsModule,
    FormsModule,
    HttpClientModule,
    MatSnackBarModule,
    MatToolbarModule,
    MatIconModule,
    MatListModule,
    CKEditorModule,
    ClipboardModule,


  ],
  providers: [AuthInterceptorProviders],
  bootstrap: [AppComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AppModule { }
