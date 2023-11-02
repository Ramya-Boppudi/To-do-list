import { Component, OnInit } from '@angular/core';
import { UserServiceService } from '../services/user-service.service';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { User } from '../models/User';
import { ToastrService } from 'ngx-toastr';
import { RouteService } from '../services/route.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  fromGroup!:FormGroup;
  user:User={};
    
  ngOnInit(): void {
   
      
  }
  constructor(private ser:UserServiceService,private forms:FormBuilder,private toastr: ToastrService,private route:RouteService){
  }


loginProcess(){
  if(this.user?.userEmail&&this.user.userPassword){console.log("inside login comp++"+this.user.userEmail);
  console.log(this.ser.login(this.user));
    this.ser.login(this.user).subscribe((val)=>{
      console.log(val);
      this.toastr.success("login Successful","Logged In");
        localStorage.setItem('token',val.token);
        this.ser.isLoggedIn=true;
        this.route.navigateToAllTask();
    });
      //next:(data:any)=>{
      //if(data.success=="200"){
        // console.log("inside log");
        // console.log(data);
        //this.toastr.success("login Successful","Logged In");
        //localStorage.setItem('token',data);
        //this.ser.isLoggedIn=true;
        //this.ser.navigateToAllTask();
      //}
     
    //},
    //error: (err) => {
      //this.toastr.error("Login Failed,Please try later!"+err)
     //}
    //});
  
  
  
  
  }
}




}
