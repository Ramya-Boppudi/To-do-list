import { Injectable } from '@angular/core';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class RouteService {

  constructor(private router:Router) { }
  navigateToDashView() {
    this.router.navigate(["main/:id"]);
  }

  navigateToLoginView(){
    this.router.navigate(["login"]);
  }
  
  navigateToAllTask(){
    console.log("inside route");
    this.router.navigate(["main"]);
  }
}
