import { UserService } from './../../services/user.service';

import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';


@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {




  errorMessage: string | undefined;
  successMessage: string | undefined;

  constructor(private UserService: UserService,private _snackBar: MatSnackBar) { }



  public user = {
    username: '',
    password: '',
    firstName: '',
    lastName: '',
    email: '',
    phoneNo: '',
    enabled:'true',
    profile:''
  }

  ngOnInit(): void {

  }

  formSubmit() {
    console.log(this.user);

    this.UserService.addUser(this.user).subscribe(
      (data) => {
        //success
        this._snackBar.open("You are successfully regestered",'',{duration:3000})
        console.log(data);
      },
      (error) => {
        //error
        console.log(error);
      }
    )
  }
}
