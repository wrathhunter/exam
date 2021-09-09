import { Component, OnInit } from '@angular/core';
import { LocationStrategy } from '@angular/common';
import { QuizServiceService } from 'src/app/services/quiz-service.service';
import { MatSnackBar } from '@angular/material/snack-bar';
@Component({
  selector: 'app-apeearquiz',
  templateUrl: './apeearquiz.component.html',
  styleUrls: ['./apeearquiz.component.css']
})
export class ApeearquizComponent implements OnInit {
  quizid:any
  qid:any
// questions:any = [];
  constructor(private locationSt: LocationStrategy,private quiz: QuizServiceService,private snak: MatSnackBar) { }

  ngOnInit(): void {
    this.qid=this.quizid
    console.log(this.qid);
  }
  // formSubmit() {
  //   this.quiz.getQuestionsOfQuiz(this.quizid).subscribe(
  //     (data: any) => {
  //       this.questions = data;
  //       console.log(data)
  //       // this.snak.open('Question Added. Add Another one','',{duration:3000,})

  //     },
  //     (error) => {
  //       console.log(error);
  //       this.snak.open('Error in getting quiz questions','',{duration:3000,})
  //     }
  //   );
  // }

  // preventBackButton() {
  //   history.pushState(null, "", location.href);
  //   this.locationSt.onPopState(() => {
  //     history.pushState(null, "", location.href);
  //   });
  // }
}
