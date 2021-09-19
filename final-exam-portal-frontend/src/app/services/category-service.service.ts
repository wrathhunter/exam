import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import baseUrl from './helper';

@Injectable({
  providedIn: 'root'
})
export class CategoryServiceService {

  constructor(private _http: HttpClient) {}

  public categories(categoryname:any) {
    return this._http.get(`${baseUrl}/examcategory/getexamcategory/${categoryname}`);
  }
  public addcategory(category: any,examname:string) {
    return this._http.post(`${baseUrl}/examcategory/createexamcategory/${examname}`, category);
  }
  public deleteCategory(categoryName:any,examName:any) {
    return this._http.delete(`${baseUrl}/examcategory/deleteexamcategory/${categoryName}/${examName}`);
  }
}
