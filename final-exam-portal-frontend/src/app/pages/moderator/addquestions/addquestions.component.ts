import { QuestionServiceService } from './../../../services/question-service.service';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import * as ClassicEditor from '@ckeditor/ckeditor5-build-classic';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-addquestions',
  templateUrl: './addquestions.component.html',
  styleUrls: ['./addquestions.component.css']
})
export class AddquestionsComponent implements OnInit {
  public Editor = ClassicEditor;

  quizname=''
  categoryname=''
  examname=''

  question = {
    content: '',
    option1: '',
    option2: '',
    option3: '',
    option4: '',
    answer: '',
  };

  constructor(private route: ActivatedRoute,private _question: QuestionServiceService,private snak: MatSnackBar) { }

  ngOnInit(): void {
    this.examname = this.route.snapshot.params.examName;
      this.categoryname = this.route.snapshot.params.categoryName;
      this.quizname = this.route.snapshot.params.quizname;
  }
  formSubmit() {
    if (this.question.content.trim() == '' || this.question.content == null) {
      return;
    }

    if (this.question.option1.trim() == '' || this.question.option1 == null) {
      return;
    }
    if (this.question.option2.trim() == '' || this.question.option2 == null) {
      return;
    }
    if (this.question.answer.trim() == '' || this.question.answer == null) {
      return;
    }

    //form submit
    this._question.addQuestion(this.question,this.quizname,this.categoryname,this.examname).subscribe(
      (data: any) => {
        this.snak.open('Question Added. Add Another one','',{duration:3000,})
        this.question.content = '';
        this.question.option1 = '';
        this.question.option2 = '';
        this.question.option3 = '';
        this.question.option4 = '';
        this.question.answer = '';
      },
      (error) => {
        this.snak.open('Error in adding question','',{duration:3000,})
      }
    );
  }
}
