import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import baseUrl from './helper';

@Injectable({
  providedIn: 'root'
})
export class ExamServiceService {

  constructor(private _http: HttpClient) {}

  public exams() {
    return this._http.get(`${baseUrl}/exam/getexam`);
  }
  public addExam(exam: any) {
    return this._http.post(`${baseUrl}/exam/createexam`, exam);
  }
}
