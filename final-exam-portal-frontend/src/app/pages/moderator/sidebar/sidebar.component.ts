import { LoginService } from 'src/app/services/login.service';
import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { UserService } from 'src/app/services/user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.css']
})
export class SidebarComponent implements OnInit {
  user :any;
  constructor(private router: Router,public userService :UserService,public login: LoginService,private snak: MatSnackBar) { }

  ngOnInit(): void {
    this.user = this.login.getUser();
  }
  deleteMyself(username:any) {
    this.userService.deleteMyself(username).subscribe(
      async (data:any) => {
        console.log(data)
       await this.login.logout();
        this.user=null
        this.ngOnInit();
       await this.router.navigateByUrl('');
       window.location.reload()
      //  this.router.navigateByUrl('');
        // this.snak.open('you got yourself deleted successsfully', '', { duration: 3000, })
      },
      (error: any) => {
         console.log(error);
      }
    );
  }
}
