import { ExamServiceService } from './../../../services/exam-service.service';
import { CategoryServiceService } from './../../../services/category-service.service';
import { Component, Input, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-viewcategories',
  templateUrl: './viewcategories.component.html',
  styleUrls: ['./viewcategories.component.css']
})
export class ViewcategoriesComponent implements OnInit {

  categories:any = [];

  constructor(private _route: ActivatedRoute,private snack:MatSnackBar,private categoryService:CategoryServiceService, private allexams:ExamServiceService) {}

  name=''
  ngOnInit(): void {
    this.name = this._route.snapshot.params.examName;

    this.categoryService.categories(this.name).subscribe(
      (data: any) => {
        //css
        this.categories = data;
        console.log(this.categories);
      },

      (error) => {
        //
        console.log(error);
        this.snack.open('Error in loading data','',{duration:3000,})
      }
    );
  }



}
