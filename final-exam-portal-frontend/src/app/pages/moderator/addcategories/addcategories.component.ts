import { CategoryServiceService } from './../../../services/category-service.service';
import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-addcategories',
  templateUrl: './addcategories.component.html',
  styleUrls: ['./addcategories.component.css']
})
export class AddcategoriesComponent implements OnInit {
  category = {
    categoryTitle: '',
    categoryDescription: '',
  };
  constructor(private _route: ActivatedRoute,private categoryService:CategoryServiceService,private snack:MatSnackBar) { }
  name=''
  ngOnInit(): void {
    this.name = this._route.snapshot.params.examName;
  }
  formSubmit() {
    if (this.category.categoryTitle.trim() == '' || this.category.categoryTitle == null) {
      this.snack.open('Title Required !!', '', {
        duration: 3000,
      });
      return;
    }

    //all done

    this.categoryService.addcategory(this.category,this.name).subscribe(
      (data: any) => {
        this.category.categoryTitle = '';
        this.category.categoryDescription = '';

        this.snack.open('Category is added successfuly','',{duration:3000,})
      },
      (error) => {
        console.log(error);

        this.snack.open('Server error !!','',{duration:3000,})
      }
    );
  }
}
