import { HttpClient, HttpClientModule } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { User } from '../models/User';
import { Observable } from 'rxjs';
import { Todo } from '../models/Todo';

@Injectable({
  providedIn: 'root'
})
export class UserServiceService {

  constructor(private http:HttpClient,private router:Router) { }

  urlpost:string="http://localhost:9000/api/v1/user/saveTask";
  urlget:string="http://localhost:9000/api/v1/register";
  urlgetonenote:string="http://localhost:9000/api/v1/user/task";
  urlupdate:string="http://localhost:9000/api/v1/user/updateTask";
  urldelete:string="http://localhost:9000/api/v1/user"

  saveData(data? :User):Observable<User> {
    return this.http.post<User>(`${this.urlget}`, data)
  }
  getNote(id?:String): Observable<Todo> {
    return this.http.get<Todo>(`${this.urlgetonenote}/${id}`);
  }

  deleteNote(id?:String):Observable<Todo>{
    return this.http.delete<Todo>(`${this.urldelete}/${id}`);
  }
  
  saveTask(data?:Todo):Observable<Todo> {
    return this.http.post<Todo>(`${this.urlpost}`, data)
  }
  
  updateTask(data?:Todo):Observable<Todo> {
    return this.http.put<Todo>(`${this.urlupdate}`, data)
  }
  
  // getTask():Observable<Array<Todo>>{
  //   return this.http.get<Array<Todo>>(`${this.urlget}`);
  // }
  urlCreateCards:string="http://localhost:9000/api/v1/user/getAllTasks";
  getTask():Observable<Array<Todo>>{
    return this.http.get<Array<Todo>>(`${this.urlCreateCards}`);
  }
  


loginurl:string="http://localhost:9000/api/v2/login"
 login(data?:User):Observable<any>{
  console.log("inside service ");
    return this.http.post(`${this.loginurl}`, data)
  }


  getToken(){
    return localStorage.getItem("token")
  }

  //saveData(data? :User):Observable<User> {
    //return this.http.post<User>(`${this.urlget}`, data)
  //}


//Auth ser
  isLoggedIn:boolean=false;

  islogin() {
  
     return this.isLoggedIn=true;
    }
 
   islogout(){
     return  this.isLoggedIn=false;
     }
}
