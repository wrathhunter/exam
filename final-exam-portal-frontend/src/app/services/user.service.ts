import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import baseUrl from './helper';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http:HttpClient) { }
  public addUser(user:any)
  {
    return this.http.post('http://localhost:8080/user/student',user);
  }
  public addModerator(user:any)
  {
    return this.http.post('http://localhost:8080/user/moderator',user);
  }
  public deleteMyself(username:any) {
    return this.http.delete(`${baseUrl}/user/${username}`);
  }
}
