import { MatSnackBar } from '@angular/material/snack-bar';
import { ExamServiceService } from './../../../services/exam-service.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-add-exam',
  templateUrl: './add-exam.component.html',
  styleUrls: ['./add-exam.component.css']
})
export class AddExamComponent implements OnInit {
  exam = {
    name: '',
    examinationDescription: '',
  };
  constructor(private examService:ExamServiceService,private snack:MatSnackBar) { }

  ngOnInit(): void {
  }

  formSubmit() {
    if (this.exam.name.trim() == '' || this.exam.name == null) {
      this.snack.open('Title Required !!', '', {
        duration: 3000,
      });
      return;
    }

    //all done

    this.examService.addExam(this.exam).subscribe(
      (data: any) => {
        this.exam.name = '';
        this.exam.examinationDescription = '';

        this.snack.open('Category is added successfuly','',{duration:3000,})
      },
      (error) => {
        console.log(error);
        
        this.snack.open('Server error !!','',{duration:3000,})
      }
    );
  }
}
