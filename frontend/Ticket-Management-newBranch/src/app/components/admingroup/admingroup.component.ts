import { HttpClient } from '@angular/common/http';
import { Component, OnInit, ViewChild } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { MatTable, MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { AdminviewgroupComponent } from '../adminviewgroup/adminviewgroup.component';

class Group {
  groupName: string;
  groupId: number;
  category: {
    categoryId:number,
    name:string
  };
  constructor(groupName:string,groupId:number,category:any){
    this.groupName=groupName;
    this.groupId=groupId;
    this.category=category;
  }
}

class Categories {
  name: string;
  categoryId: number;
  groups:any;
  ticket:any;
  constructor(name:string,categoryId:number){
    this.name=name;
    this.categoryId=categoryId;
  }
}


@Component({
  selector: 'app-admingroup',
  templateUrl: './admingroup.component.html',
  styleUrls: ['./admingroup.component.css']
})
export class AdmingroupComponent implements OnInit {

  formGroup: FormGroup = new FormGroup({
    name: new FormControl('',Validators.required),
    categoryId: new FormControl('',Validators.required),
    categoryName: new FormControl('',Validators.required),
  })


  categories:Categories[]=[];

  

  @ViewChild(MatTable, { static: true })
  table!: MatTable<any>;

  collection: Group[] = [];

  searchtext:string ="";


  constructor(public dialog: MatDialog, private http:HttpClient,public router: Router) {


    


      
    let url2="http://localhost:8080/group";
    this.http.get<any>(url2).subscribe(
      response=>{
        console.log(response);
        this.collection=response;
        this.dataSource=new MatTableDataSource(this.collection);
        console.log(this.collection);
        this.table.renderRows();
      }
    )




   }

  ngOnInit(): void {
  }

  displayedColumns: string[] = ['sno', 'name','category'];
 
  dataSource=new MatTableDataSource(this.collection);
  
  add(){
    let adminemail=sessionStorage.getItem("email") || "";
    const headers = { 'email': adminemail};
    let url="http://localhost:8080/group";

    this.http.post(url, {
      groupName:this.formGroup.value.name,
      category:{
        categoryId: this.formGroup.value.categoryId,
        name:this.formGroup.value.categoryName
      }
    },{headers}).toPromise().then((data:any)=>{
      console.log(data);
     
    });
    this.router.navigate(['/admingroup']).then(() => {
    window.location.reload();
  });
  
  
  }

  applyFilter(event: any){
    this.dataSource.filter = event.target.value.trim().toLowerCase();
  }

  openViewGroupDialog(){
    this.dialog.open(AdminviewgroupComponent);
  }



logout(){
  sessionStorage.setItem("email","");
}


}


