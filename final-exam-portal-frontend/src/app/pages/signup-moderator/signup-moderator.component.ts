import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-signup-moderator',
  templateUrl: './signup-moderator.component.html',
  styleUrls: ['./signup-moderator.component.css']
})
export class SignupModeratorComponent implements OnInit {
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

    this.UserService.addModerator(this.user).subscribe(
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
