import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { MatTable, MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';

class Ticket {
  ticketId: number;
  status: string;
  priority: string;
  createdByUserId: number;
  title: string;
  startDate: Date;
  dueDate: Date;
  description: string;
  comments: any;
  attachments: any;
  category: {
    "categoryId": number,
    "name": string
  };
  user: any;
  constructor(ticketId: number, status: string, priority: string, createdByUserId: number, title: string, startDate: Date, dueDate: Date, description: string, category: any) {
    this.ticketId = ticketId;
    this.createdByUserId = createdByUserId;
    this.title = title;
    this.startDate = startDate;
    this.status = status;
    this.dueDate = dueDate;
    this.priority = priority;
    this.description = description;
    this.category = category;
  }
}

@Component({
  selector: 'app-userhome',
  templateUrl: './userhome.component.html',
  styleUrls: ['./userhome.component.css']
})
export class UserhomeComponent implements OnInit {

  selectedFile: any;
  collection: Ticket[] = [];
  dataSource: any;
  table!: MatTable<any>;
  ipdata: string = '';
  formGroup: FormGroup = new FormGroup({
    ipdata: new FormControl('', Validators.required)
  });
  private _snackBar: any;
  displayedColumns: string[] = ['sno', 'title', 'description', 'categoryId', 'categoryName', 'upload', 'append'];
  //dataSource = new MatTableDataSource(ELEMENT_DATA);

  constructor(public dialog: MatDialog, public router: Router, private http: HttpClient) {

    let adminemail = sessionStorage.getItem("email") || "";
    const headers = { 'email': "user1@gmail.com" };

    let url = "http://localhost:8080/ticket";
    this.http.get<any>(url, { headers }).subscribe(
      response => {
        console.log(response);
        this.collection = response;
        this.dataSource = new MatTableDataSource(this.collection);
        console.log(this.collection);
        //this.table.renderRows();
      }
    )


  }

  ngOnInit(): void {
  }

  applyFilter(event: any) {
    this.dataSource.filter = event.target.value.trim().toLowerCase();
  }
  logout() {
    sessionStorage.setItem("email", "");
  }
  onFileSelected(event: any) {
    console.log(event);
    this.selectedFile = event.target.files[0];
  }
  /*
  openDialog(): void {
    this.dialog.open(AdminviewticketComponent);
  }
  */
  upload(id: number) {
    console.log(id);
    /*
    const headers ={
      'email' : 'member1@gmail.com'
    };
    */

    let adminemail = sessionStorage.getItem("email") || "";
    const headers = { 'email': adminemail };
    const fd = new FormData();
    fd.append('file', this.selectedFile!);
    let url = 'http://localhost:8080/ticket/' + id + '/uploadAttachment';
    this.http.put(url, fd, { headers })
      .subscribe(
        res => {
          console.log(res);
        });
    alert("uploaded");
  }
  onSubmit(id: number) {
    console.log(this.formGroup.value.ipdata);

    let adminemail = sessionStorage.getItem("email") || "";
    const headers = { 'email': adminemail };
    /*
    const headers ={
      'email' : 'user1@gmail.com'
    };
    */

    let url = "http://localhost:8080/ticket/" + id;

    this.http.put(url, {
      description: this.formGroup.value.ipdata

    }, { headers }).toPromise().then((data: any) => {
      console.log(data)
    });

    alert("submitted");

  }

}
