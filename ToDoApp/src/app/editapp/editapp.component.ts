import { Component } from '@angular/core';
import { Todo } from '../models/Todo';
import { UserServiceService } from '../services/user-service.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatDialog } from '@angular/material/dialog';
import { ActivatedRoute } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { RouteService } from '../services/route.service';

@Component({
  selector: 'app-editapp',
  templateUrl: './editapp.component.html',
  styleUrls: ['./editapp.component.css']
})
export class EditappComponent {
  // formdata:Todo={};
  minDate = new Date();
  formdata: Todo = {taskId:'', taskName:'', priority: '',category:'',taskDesc:'',dueDate:this.minDate};
  constructor(private ser:UserServiceService,private snackbar:MatSnackBar,private dig:MatDialog,private activatedRoute:ActivatedRoute,private route:RouteService,private toastr: ToastrService ){}
  
  checkbeforeexit() {
    if ((this.formdata.taskName || this.formdata.priority || this.formdata.dueDate || this.formdata.category || this.formdata.taskDesc)!=null){
      if(confirm("Do you want to exit upadting card. All Progress will be Lost!")){
        this.route.navigateToAllTask();
      } 
      return true;
    }
    else{
      this.dig.closeAll();
      return true;
    }
  }
 

  ngOnInit(): void {
    console.log("esit log");
    this.activatedRoute.paramMap.subscribe((params) => {
      let taskId=params.get("id")??'0';
      console.log(taskId);
      this.ser.getNote(taskId).subscribe((data:any) => {
        this.formdata = data;
      });
    });
  }

update(){
if (this.formdata.taskName && this.formdata.priority && this.formdata.dueDate && this.formdata.category &&this.formdata.taskDesc){
      this.formdata.status="pending";
  
      
    this.ser.updateTask(this.formdata).subscribe({
      next: data => {
        this.route.navigateToAllTask();
        this.toastr.success("Task update  completed","UPDATED");
      },
      error:err => {
          
        this.toastr.error("Task could not be saved","Update Failed");
      }
      })
  }


  

}


logoutf(){
  localStorage.removeItem('token');
  this.ser.isLoggedIn=false;
  this.route.navigateToLoginView();

  
}

}