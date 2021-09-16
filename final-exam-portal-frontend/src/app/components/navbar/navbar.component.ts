import { Route } from '@angular/compiler/src/core';
import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router, RouterModule } from '@angular/router';
import { LoginService } from 'src/app/services/login.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {
  isLogedIn=false;
  user:any;

  constructor(public login:LoginService,private router:Router,private snack:MatSnackBar) { }

  ngOnInit(): void {
    this.isLogedIn=this.login.isLoggedIn();
    this.user=this.login.getUser();
    this.login.loginStatusSubject.asObservable().subscribe((data)=>{
      this.isLogedIn=this.login.isLoggedIn();
      this.user=this.login.getUser();
    })
    }

  public logout()
  {
    this.login.logout();
    this.user=null;
    // window.location.reload();
    this.ngOnInit();
    this.router.navigateByUrl('');
    this.snack.open('You are successfully logged out','',{duration:3000,})

    //window.location.reload();
  }

}
