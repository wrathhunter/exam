import { LocationStrategy } from '@angular/common';
import { ThrowStmt } from '@angular/compiler';
import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute } from '@angular/router';
import { QuizServiceService } from 'src/app/services/quiz-service.service';

@Component({
  selector: 'app-start',
  templateUrl: './start.component.html',
  styleUrls: ['./start.component.css']
})
export class StartComponent implements OnInit {
  qid: any;
  questions: any
  timer: any;
  isSubmit = false;
  marksGot = 0;
  correctAnswers = 0;
  attempted = 0;
  quizmaxmarks: any
  quizz: any;
  temp: any
  noOfAttemptsOnQuizInteger=0;
  time:any



  constructor(private locationSt: LocationStrategy,
    private _route: ActivatedRoute, private quiz: QuizServiceService, private snak: MatSnackBar) { }

  ngOnInit(): void {
    this.preventBackButton();
    this.qid = this._route.snapshot.params.quizid;
    this.quiz.getQuiz(this.qid).subscribe(
      (data: any) => {
        // console.log(data)
        this.quizz = data
        this.quizmaxmarks = data.quizMaxMarks
        // console.log(this.quizmaxmarks)
      },
      (error) => {
        console.log(error);
        this.snak.open('quiz not found', '', { duration: 3000, })
      }
    );
    this.loadQuestions();

  }
  loadQuestions() {
    this.quiz.getQuestionsOfQuiz(this.qid).subscribe(
      (data: any) => {
        console.log(data)
        this.questions = data
        this.time=data.length

        this.timer = this.questions.length * 2 * 60;
        // if (Array.isArray(this.questions)) {
        //   this.questions.forEach(q => { q['quizmaxmarks']=this.quizmaxmarks });
        // }
        this.startTimer();
      },
      (error) => {
        console.log(error);
        this.snak.open('No quiz with this Id.', '', { duration: 3000, })
      }
    );
  }

  preventBackButton() {
    history.pushState(null, "", location.href);
    this.locationSt.onPopState(() => {
      history.pushState(null, "", location.href);
    });
  }
  startTimer() {
    let t = window.setInterval(() => {
      //code
      if (this.timer <= 0) {
        this.submitQuiz();
        clearInterval(t);
      } else {
        this.timer--;
      }
    }, 1000);
  }
  evalQuiz() {
    // let alphas:object[]=[this.questions,this.quizmaxmarks];
    // this.temp.push(this.questions)
    // this.temp.push(this.quizmaxmarks)
    if (Array.isArray(this.questions)) {
      this.questions.forEach(q => { q.quizmaxmarks = this.quizmaxmarks });
    }
    this.quiz.evalquiz(this.questions).subscribe(
      (data: any) => {
        console.log(data);
        // this.marksGot=parseFloat(Number(data.marksGot).toFixed(2));
        this.marksGot=data.marksGot
        this.correctAnswers=data.correctAnswers
        this.attempted=data.attempted
        this.noOfAttemptsOnQuizInteger=data.noOfAttemptsOnQuizInteger
        this.isSubmit=true
      },
      (error) => {
        console.log(error);
      }
    );

  }
  submitQuiz() {
    this.evalQuiz();
  }
  getFormattedTime() {
    let mm = Math.floor(this.timer / 60);
    let ss = this.timer - mm * 60;
    return `${mm} min : ${ss} sec`;
  }
  printPage() {
    window.print();
  }
}
