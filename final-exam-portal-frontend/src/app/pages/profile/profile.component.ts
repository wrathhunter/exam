import { Component, OnInit } from '@angular/core';
import { LoginService } from 'src/app/services/login.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  user:any
  x:any="https://media.gettyimages.com/photos/russian-actress-irina-starshenbaum-poses-on-may-10-2018-during-a-for-picture-id956785212?s=612x612"
  constructor(private login:LoginService) { }

  ngOnInit(): void {
    this.user=this.login.getUser();
    console.log(this.user)
    this.x=this.user.profile

  }


}
