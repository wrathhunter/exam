import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import baseUrl from './helper';

@Injectable({
  providedIn: 'root'
})
export class QuestionServiceService {

  constructor(private _http: HttpClient) { }

  public getQuestionsOfQuiz(quizname:any,examcatname:any,examname:any) {
    return this._http.get(`${baseUrl}/question/getquestions/${quizname}/${examcatname}/${examname}`);
  }
  public addQuestion(question:any,quizname:any,examcatname:any,examname:any) {
    return this._http.post(`${baseUrl}/question/createquestion/${quizname}/${examcatname}/${examname}`, question);
  }

  public deleteQuestion(questionId:any,quizName:any,categoryName:any,examName:any) {
    return this._http.delete(`${baseUrl}/question/deletequestion/${questionId}/${quizName}/${categoryName}/${examName}`);
  }

  // public getQuestionsOfQuizForTest(qid) {
  //   return this._http.get(`${baseUrl}/question/quiz/${qid}`);
  // }

  //add question

  // //delete question
  // public deleteQuestion(questionId) {
  //   return this._http.delete(`${baseUrl}/question/${questionId}`);
  // }

  // //eval quiz
  // public evalQuiz(questions) {
  //   return this._http.post(`${baseUrl}/question/eval-quiz`, questions);
  // }
}
