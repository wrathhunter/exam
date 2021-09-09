import { QuizServiceService } from './../../../services/quiz-service.service';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';
import { CategoryServiceService } from 'src/app/services/category-service.service';
import { ExamServiceService } from 'src/app/services/exam-service.service';

@Component({
  selector: 'app-viewquizs',
  templateUrl: './viewquizs.component.html',
  styleUrls: ['./viewquizs.component.css']
})
export class ViewquizsComponent implements OnInit {
  quizzes:any = [];
  constructor(private quizService:QuizServiceService,private _route: ActivatedRoute,private snack:MatSnackBar,private categoryService:CategoryServiceService, private allexams:ExamServiceService) { }
  examname=''
  categoryname=''
  ngOnInit(): void {
    this.examname = this._route.snapshot.params.examName;
    this.categoryname = this._route.snapshot.params.categoryName;

    this.quizService.quizzes(this.categoryname,this.examname).subscribe(
      (data: any) => {
        //css
        this.quizzes = data;
        console.log(this.quizzes);
      },

      (error) => {
        //
        console.log(error);
        this.snack.open('Error in loading data','',{duration:3000,})
      }
    );
  }

}
