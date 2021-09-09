import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute } from '@angular/router';
import { CategoryServiceService } from 'src/app/services/category-service.service';
import { ExamServiceService } from 'src/app/services/exam-service.service';
import { QuizServiceService } from 'src/app/services/quiz-service.service';

@Component({
  selector: 'app-addquizs',
  templateUrl: './addquizs.component.html',
  styleUrls: ['./addquizs.component.css']
})
export class AddquizsComponent implements OnInit {
  quiz = {
    quizTitle: '',
    quizDescription: '',
    quizMaxMarks:'',
    quizNoOfQuestions:''
  };
  constructor(private _route: ActivatedRoute,private snack:MatSnackBar,private categoryService:CategoryServiceService, private allexams:ExamServiceService,private quizService:QuizServiceService) { }
  examName=''
  categoryName=''
  ngOnInit(): void {
    this.examName = this._route.snapshot.params.examName;
    this.categoryName = this._route.snapshot.params.categoryName;
  }
  formSubmit() {
    if (this.quiz.quizTitle.trim() == '' || this.quiz.quizTitle == null) {
      this.snack.open('Title Required !!', '', {
        duration: 3000,
      });
      return;
    }

    //all done

    this.quizService.addQuiz(this.quiz,this.categoryName,this.examName).subscribe(
      (data: any) => {
        this.quiz.quizTitle = '';
        this.quiz.quizDescription = '';
        this.quiz.quizMaxMarks = '';
        this.quiz.quizNoOfQuestions = '';

        this.snack.open('Category is added successfuly','',{duration:3000,})
      },
      (error) => {
        console.log(error);

        this.snack.open('Server error !!','',{duration:3000,})
      }
    );
  }
}
