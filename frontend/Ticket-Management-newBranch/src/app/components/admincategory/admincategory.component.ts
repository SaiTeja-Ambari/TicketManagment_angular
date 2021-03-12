import { Component, OnInit, ViewChild } from '@angular/core';
import { MatTable, MatTableDataSource } from '@angular/material/table';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import {HttpClient} from '@angular/common/http';
import { identifierModuleUrl } from '@angular/compiler';
import { NavigationEnd, Router } from '@angular/router';


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
  selector: 'app-admincategory',
  templateUrl: './admincategory.component.html',
  styleUrls: ['./admincategory.component.css']
})



export class AdmincategoryComponent implements OnInit {

  

  formGroup: FormGroup = new FormGroup({
    name: new FormControl('',Validators.required)
  })

  @ViewChild(MatTable, { static: true })
  table!: MatTable<any>;

  collection: Categories[] = [];

  searchtext:string ="";

  dataSource:any;
  
  constructor(private http:HttpClient,public router: Router) {

    let url="http://localhost:8080/category";
    this.http.get<any>(url).subscribe(
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

  displayedColumns: string[] = ['categoryId', 'name'];
 
  
  
  add(){
  
    
    let url="http://localhost:8080/category";

    this.http.post(url, {
      name:this.formGroup.value.name
    }).toPromise().then((data:any)=>{
      console.log(data);
     
    });
    this.router.navigate(['/admincategory']).then(() => {
    window.location.reload();
  });
  
  
  
  }

  applyFilter(event: any){
    this.dataSource.filter = event.target.value.trim().toLowerCase();
  }
 
  logout(){
    sessionStorage.setItem("email","");
  }

}
