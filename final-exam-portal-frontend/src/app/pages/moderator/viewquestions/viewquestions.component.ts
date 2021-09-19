import { QuestionServiceService } from './../../../services/question-service.service';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-viewquestions',
  templateUrl: './viewquestions.component.html',
  styleUrls: ['./viewquestions.component.css']
})
export class ViewquestionsComponent implements OnInit {
  quizname=''
  categoryname=''
  examname=''
  questions:any = [];
  constructor(  private route: ActivatedRoute,
    private question: QuestionServiceService,
    private snak: MatSnackBar) { }

    ngOnInit(): void {
      this.examname = this.route.snapshot.params.examName;
      this.categoryname = this.route.snapshot.params.categoryName;
      this.quizname = this.route.snapshot.params.quizname;
      this.question.getQuestionsOfQuiz(this.quizname,this.categoryname,this.examname)
      .subscribe(
        (data: any) => {
          console.log(data);
          this.questions = data;
        },
        (error) => {
          console.log(error);
        }
      );
    }
    deleteQuestion(questionid:any,quizname:any,categoryname: any,examname:any) {
      this.question.deleteQuestion(questionid,quizname,categoryname,examname).subscribe(
        (data:any) => {
          this.snak.open('Question deleted successfully', '', { duration: 3000, })
          console.log(data)
          this.ngOnInit();
        },
        (error) => {
           console.log(error);
        }
      );
    }
}
