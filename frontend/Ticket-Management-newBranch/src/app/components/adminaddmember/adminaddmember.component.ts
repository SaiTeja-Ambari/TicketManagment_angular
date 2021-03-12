import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatTable, MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';


class User{
  userId:number;
  name:string;
  email:string;
  password:string;
  type:string;
  groups:any;
  constructor(userId:number, name:string, email:string, password:string, type:string){
    this.userId=userId;
    this.name=name;
    this.email=email;
    this.password=password;
    this.type=type;
  }
}

@Component({
  selector: 'app-adminaddmember',
  templateUrl: './adminaddmember.component.html',
  styleUrls: ['./adminaddmember.component.css']
})
export class AdminaddmemberComponent implements OnInit {

  formGroup: FormGroup = new FormGroup({
    memberId: new FormControl('',Validators.required),
    groupId: new FormControl('',Validators.required)
  })

  table!: MatTable<any>;

  collection: User[] = [];

  searchtext:string ="";

  dataSource:any;

  constructor(private http:HttpClient,public router: Router) {
    let url="http://localhost:8080/user/member";
    this.http.get<any>(url).subscribe(
      response=>{
        console.log(response);
        this.collection=response;
        this.dataSource=new MatTableDataSource(this.collection);
        console.log(this.collection);
      }
    )
   }

  ngOnInit(): void {
  }
  displayedColumns: string[] = ['userId', 'name'];
  add(){

    let url="http://localhost:8080/group/"+this.formGroup.value.groupId+"/member";
    console.log(this.formGroup.value.userId);
    this.http.put(url, {
      userId:this.formGroup.value.memberId
    }).toPromise().then((data:any)=>{
      console.log(data);
     
    });
    
}

applyFilter(event: any){
  this.dataSource.filter = event.target.value.trim().toLowerCase();
}

logout(){
  sessionStorage.setItem("email","");
}

}