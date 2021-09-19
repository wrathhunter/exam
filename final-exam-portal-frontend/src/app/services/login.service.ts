import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  public loginStatusSubject=new Subject<boolean>();

  constructor(private router: Router,private http: HttpClient) { }
  public getCurrentUser()
  {
    return this.http.get('http://localhost:8080/current-user');
  }
//generate token
  public generateToken(loginData:any)
  {
    return this.http.post('http://localhost:8080/generate-token',loginData);
  }
  //set token to local storage
  public loginUser(token:any)
  {
    localStorage.setItem('token',token);
    //this.loginStatusSubject.next(true);
    return true;
  }
  //check if he is logged in
  public isLoggedIn()
  {
    let tokenStr=localStorage.getItem('token');
    if(tokenStr==undefined || tokenStr==null || tokenStr=='')
    {
      return false;
    }
    else{
      return true;
    }
  }
  //log him out
  public logout()
  {
    localStorage.removeItem('token');
    localStorage.removeItem('user');

    return true;
  }
  //to get the token
  public getToken()
  {
    return localStorage.getItem('token');
  }
  //store user
  public setUser(user:any)
  {
    localStorage.setItem('user',JSON.stringify(user));
  }
  //keep user
  public getUser()
  {
    let userStr=localStorage.getItem('user');
    if(userStr!=null)
    {
      return JSON.parse(userStr);
    }
    else
    {
      this.logout();
      return null;
    }
  }
  //get the role of user
  public getUserRole()
  {
    let user=this.getUser();
    return user.authorities[0].authority;
  }
}
