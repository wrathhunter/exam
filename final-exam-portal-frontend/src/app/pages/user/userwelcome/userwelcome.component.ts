import { QuizServiceService } from './../../../services/quiz-service.service';
import { LoginService } from './../../../services/login.service';
import { Component, OnInit } from '@angular/core';
import { JsonpClientBackend } from '@angular/common/http';

@Component({
  selector: 'app-userwelcome',
  templateUrl: './userwelcome.component.html',
  styleUrls: ['./userwelcome.component.css']
})
export class UserwelcomeComponent implements OnInit {
  user: any
  listOfQuizes: any
  quizzes:any = [];
  constructor(private login: LoginService, private quiz: QuizServiceService) { }

  ngOnInit(): void {
    this.user = this.login.getUser();
    this.listOfQuizes = this.user.idOfAppearedQuizes;

    for (var val of this.listOfQuizes) {
      this.quiz.getQuiz(val).subscribe(
        (data: any) => {
          this.quizzes.push(data);
          console.log(this.quizzes);
        },

        (error) => {
          console.log(error);
        }
      );
    }
  }

}
