import { QuizServiceService } from 'src/app/services/quiz-service.service';
import { Component, OnInit } from '@angular/core';
import { LoginService } from 'src/app/services/login.service';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-welcome',
  templateUrl: './welcome.component.html',
  styleUrls: ['./welcome.component.css']
})
export class WelcomeComponent implements OnInit {
  user: any
  categories: any
  examinations: any
  quizs: any
  k: any
  k1: any

  creatersQuizzes: any = [];
  temp: any = [];
  mostfavquiztitle: any
  leastfavquiztitle: any
  lengthoflistofquiz: any
  constructor(private login: LoginService, private quiz: QuizServiceService, private snack: MatSnackBar) { }

  ngOnInit(): void {
    this.k = 0;
    this.k1 = 0;
    this.user = this.login.getUser()
    this.examinations = this.user.examinations.length
    for (var i = 0; i < this.examinations; i++) {
      var m = this.user.examinations[i].examinationCategories.length
      this.k = this.k + m
    }
    this.categories = this.k;
    for (var i = 0; i < this.examinations; i++) {
      for (var j = 0; j < this.user.examinations[i].examinationCategories.length; j++) {
        var n = this.user.examinations[i].examinationCategories[j].quizzes.length
        this.k1 = this.k1 + n
      }
    }
    this.quizs = this.k1

    this.quiz.getQuizzesofCreater(this.user.id).subscribe(
      (data: any) => {

        // console.log(data)

        this.lengthoflistofquiz = data.length

        this.creatersQuizzes = data;


        for (var i = 0; i < this.lengthoflistofquiz; i++) {
          this.temp.push(this.creatersQuizzes[i].noOfAttempts);
        }

        const max = this.temp.reduce((a: number, b: number) => Math.max(a, b));
        const min = this.temp.reduce((a: number, b: number) => Math.min(a, b));
        var largest = 0;
        for (var i = 1; i < this.lengthoflistofquiz; i++) {
          if (this.creatersQuizzes[i].noOfAttempts > this.creatersQuizzes[largest].noOfAttempts) largest = i;
        }
        this.mostfavquiztitle = this.creatersQuizzes[largest].quizTitle

        var least = 0;
        for (var i = 1; i < this.lengthoflistofquiz; i++) {
          if (this.creatersQuizzes[i].noOfAttempts < this.creatersQuizzes[least].noOfAttempts) least = i;
        }
        this.leastfavquiztitle = this.creatersQuizzes[least].quizTitle

      },
      (error) => {
        console.log(error);
      }
    );





  }



}
