import { CategoryServiceService } from './../../../services/category-service.service';
import { ExamServiceService } from './../../../services/exam-service.service';
import { Component, Input, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';

@Component({
  selector: 'app-view-exam',
  templateUrl: './view-exam.component.html',
  styleUrls: ['./view-exam.component.css']
})
export class ViewExamComponent implements OnInit {
  exams: any = [];

  constructor(private router:Router,private snack: MatSnackBar, private examService: ExamServiceService, private categoryservice: CategoryServiceService) { }
  ngOnInit(): void {

    this.examService.exams().subscribe(
      (data: any) => {
        this.exams = data;
        // console.log(this.exams);
      },

      (error) => {
        // console.log(error);
        this.snack.open('Error in loading data', '', { duration: 3000, })
      }
    );
  }

  deleteQuiz(examid: any) {
    this.examService.deleteQuiz(examid).subscribe(
      (data:any) => {
        this.snack.open('Exam deleted successfully', '', { duration: 3000, })
        console.log(data)
        this.ngOnInit();
      },
      (error) => {
         console.log(error);
      }
    );
  }
}

