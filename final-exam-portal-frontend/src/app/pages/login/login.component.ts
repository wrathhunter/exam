import { LoginService } from './../../services/login.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  temp:any

  loginData={
    username:'',
    password:''
  }
  constructor(private snack:MatSnackBar,private login:LoginService,private router:Router) { }

  ngOnInit(): void {
  }
  formSubmit()
  {
    console.log("login button clicked");
    if(this.loginData.username.trim()==''|| this.loginData.username==null)
    {
      this.snack.open('UserName is Required','',{duration:3000,})
      return;
    }
    if(this.loginData.password.trim()==''|| this.loginData.password==null)
    {
      this.snack.open('Password is Required','',{duration:3000,})
      return;
    }
    this.login.generateToken(this.loginData).subscribe(
      (data) => {
        //success
        this.snack.open("You are successfully logged in",'',{duration:3000})
        console.log(data);
        // var temp:any = "John";
        this.temp=data;
        this.login.loginUser(this.temp.token);

        this.login.getCurrentUser().subscribe(
          (user:any)=>{
            this.login.setUser(user);
            console.log(user)
            if(this.login.getUserRole()=='ROLE_MODERATOR')
            {
              this.router.navigate(['moderator-dashboard']);
              this.login.loginStatusSubject.next(true);
              // window.location.href='/moderator-dashboard'
            }
            else if(this.login.getUserRole()=='ROLE_USER'){
              this.router.navigate(['user-dashboard']);
              this.login.loginStatusSubject.next(true);
              //window.location.href='/user-dashboard'
            }
            else if(this.login.getUserRole()=='ROLE_ADMIN'){
              this.router.navigate(['admin-dashboard']);
              this.login.loginStatusSubject.next(true);
              // window.location.href='/admin-dashboard'
            }
            else{
              this.login.logout();
            //  location.reload();
            }

          }
        )
      },
      (error) => {
        //error
        this.snack.open("Invalid Credentials, please try again!",'',{duration:3000})
        console.log(error);
      }
    )

  }

}
