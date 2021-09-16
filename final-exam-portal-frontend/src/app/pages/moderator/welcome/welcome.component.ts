import { Component, OnInit } from '@angular/core';
import { LoginService } from 'src/app/services/login.service';

@Component({
  selector: 'app-welcome',
  templateUrl: './welcome.component.html',
  styleUrls: ['./welcome.component.css']
})
export class WelcomeComponent implements OnInit {
  user:any
  categories:any
  examinations:any
  quizs:any
  k:any
  k1:any
  constructor(private login:LoginService) { }

  ngOnInit(): void {
    this.k=0;
    this.k1=0;
    this.user=this.login.getUser()
    this.examinations=this.user.examinations.length
    for(var i=0;i<this.examinations;i++)
    {
      var m=this.user.examinations[i].examinationCategories.length
      this.k=this.k+m
    }
    this.categories=this.k;
    for(var i=0;i<this.examinations;i++)
    {
      for(var j=0;j<this.user.examinations[i].examinationCategories.length;j++)
      {
        var n=this.user.examinations[i].examinationCategories[j].quizzes.length
        this.k1=this.k1+n
      }
    }
    this.quizs=this.k1
    console.log(this.user.examinations[0].examinationCategories.length)
  }

}
