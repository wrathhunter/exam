import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import baseUrl from './helper';

@Injectable({
  providedIn: 'root'
})
export class QuizServiceService {

  constructor(private _http: HttpClient) {}

  public quizzes(examCategoryName:any,examName:any) {
    return this._http.get(`${baseUrl}/quiz/getallquiz/${examCategoryName}/${examName}`);
  }
  public addQuiz(quiz:any,examCategoryName:any,examName:any) {
    return this._http.post(`${baseUrl}/quiz/createquiz/${examCategoryName}/${examName}`, quiz);
  }
  public getQuestionsOfQuiz(quizid:any)
  {
    return this._http.get(`${baseUrl}/quiz/getquestion/${quizid}`);
  }
  public getQuiz(quizid:any)
  {
    return this._http.get(`${baseUrl}/quiz/getexactquiz/${quizid}`);
  }
}
