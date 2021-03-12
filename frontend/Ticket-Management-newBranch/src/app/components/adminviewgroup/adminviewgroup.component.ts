import { Component, OnInit, ViewChild } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatTable, MatTableDataSource } from '@angular/material/table';


export interface Members {
  name: string
}



@Component({
  selector: 'app-adminviewgroup',
  templateUrl: './adminviewgroup.component.html',
  styleUrls: ['./adminviewgroup.component.css']
})
export class AdminviewgroupComponent implements OnInit {

  formGroup: FormGroup = new FormGroup({
    name: new FormControl('',Validators.required)
  })


  @ViewChild(MatTable, { static: true })
  table!: MatTable<any>;

  collection: Members[] = [
    { name: 'Hydrogen'},
    { name: 'Helium'},
    { name: 'Lithium'},
    { name: 'Beryllium'},
    { name: 'Boron'},
    { name: 'Carbon'},
    { name: 'Nitrogen'},
    { name: 'Oxygen'},
    { name: 'Fluorine'},
    { name: 'Neon'},
  ];

  constructor() { }

  ngOnInit(): void {
  }

  displayedColumns: string[] = ['name'];
  dataSource = new MatTableDataSource(this.collection);

  add(){
    this.dataSource.data.push({name:this.formGroup.value.name});  
    this.table.renderRows();
  }

  logout(){
    sessionStorage.setItem("email","");
  }


}
