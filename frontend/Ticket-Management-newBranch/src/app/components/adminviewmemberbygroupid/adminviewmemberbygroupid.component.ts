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
  selector: 'app-adminviewmemberbygroupid',
  templateUrl: './adminviewmemberbygroupid.component.html',
  styleUrls: ['./adminviewmemberbygroupid.component.css']
})
export class AdminviewmemberbygroupidComponent implements OnInit {

  formGroup: FormGroup = new FormGroup({
    groupId: new FormControl('',Validators.required)
  })

  table!: MatTable<any>;

  collection: User[] = [];

  searchtext:string ="";

  dataSource:any;

  constructor(private http:HttpClient,public router: Router) { }

  ngOnInit(): void {
  }

  show(){
    let url="http://localhost:8080/group/"+this.formGroup.value.groupId+"/member";
    this.http.get<any>(url).subscribe(
      response=>{
        console.log(response);
        this.collection=response;
        this.dataSource=new MatTableDataSource(this.collection);
        console.log(this.collection);
      }
    )
  }

  applyFilter(event: any){
    this.dataSource.filter = event.target.value.trim().toLowerCase();
  }

  logout(){
    sessionStorage.setItem("email","");
  }

  displayedColumns: string[] = ['userId', 'name'];

}
