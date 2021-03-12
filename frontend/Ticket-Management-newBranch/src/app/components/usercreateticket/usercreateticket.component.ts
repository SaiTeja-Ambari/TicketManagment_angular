import { HttpClient } from '@angular/common/http';
import { Component, OnInit, ViewChild } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';


class Ticket{
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
  constructor(ticketId:number,status:string,priority:string,createdByUserId:number,title:string,startDate:Date,dueDate:Date,description:string,category:any){
    this.ticketId=ticketId;
    this.createdByUserId=createdByUserId;
    this.title=title;
    this.startDate=startDate;
    this.status=status;
    this.dueDate=dueDate;
    this.priority=priority;
    this.description=description;
    this.category=category;
  }
}
class attachments{
  attachment_id : number;
  attachment : File;
  attachment_name : string;
  attachment_type : string;
  ticket_ticket_id : number;
  user_id_user_id : number;
  constructor(attachment_id : number,
    attachment : File,
    attachment_name : string,
    attachment_type : string,
    ticket_ticket_id : number,
    user_id_user_id : number
    )
    {
      this.attachment_id=attachment_id;
      this.attachment=attachment;
      this.attachment_name=attachment_name;
      this.attachment_type=attachment_type;
      this.ticket_ticket_id=ticket_ticket_id;
      this.user_id_user_id=user_id_user_id;
    }
}

@Component({
  selector: 'app-usercreateticket',
  templateUrl: './usercreateticket.component.html',
  styleUrls: ['./usercreateticket.component.css']
})
export class UsercreateticketComponent implements OnInit {
  selectedFile = null;
  formGroup: FormGroup = new FormGroup({
    ticketId : new FormControl('',Validators.required), 
    title: new FormControl('',Validators.required),
    description: new FormControl('',Validators.required),
    categoryId: new FormControl('',Validators.required),
    categoryName: new FormControl('',Validators.required),
    startDate : new FormControl('',Validators.required),
    attachment : new FormControl('',Validators.required),
    
    
  })


  fileToUpload: any ;
  constructor(private http : HttpClient,private _snackBar: MatSnackBar) { 
  }

  ngOnInit(): void {
  }
  submit(){
    console.log(this.formGroup.value.ticketId);
    console.log(this.formGroup.value.title);
    console.log(this.formGroup.value.description);
    console.log(this.formGroup.value.categoryId);
    console.log(this.formGroup.value.categoryName);
    /*
    const headers ={
      'email' : 'user1@gmail.com'
    };
    */
   
    let adminemail=sessionStorage.getItem("email") || "";
    const headers = { 'email': adminemail};
    let url="http://localhost:8080/ticket";

    this.http.post(url, {
      ticketId : this.formGroup.value.ticketId,
      title:this.formGroup.value.title,
      description:this.formGroup.value.description,
      category : {
      categoryId:this.formGroup.value.categoryId,
      name:this.formGroup.value.categoryName,
     // attachment : this.formGroup.value.attachment
      }
    },{headers}).toPromise().then((data:any)=>{
      console.log(data)
    });
    this.formGroup.reset();

    this._snackBar.open('Ticket Created','Ok',{duration:3000});

  }
  onFileSelected(event : any){
    console.log(event);
    this.selectedFile=event.target.files[0];
  }
  onUpload(){
    
    console.log(this.selectedFile);
    let adminemail=sessionStorage.getItem("email") || "";
    const headers = { 'email': adminemail};

    const fd =new FormData();
    //let url='http://localhost:8080/ticket/2/uploadAttachment';
    
    fd.append('file',this.selectedFile!);
    console.log(this.selectedFile);
    
    this.http.put('http://localhost:8080/ticket/'+this.formGroup.value.ticketId+'/uploadAttachment',fd,{headers})
    .subscribe(
      res =>  {
        console.log(res);
      });
     /*
      this.http
      .post('http://localhost:8080/ticket/'+this.formGroup.value.ticketId+'/uploadAttachment', fd, { headers })
     
      
    this.http.post(url, {
      attachment_id :0
     // attachment : this.formGroup.value.attachment
      }
    ,{headers}).toPromise().then((data:any)=>{
      console.log(data)
    });
    */
  }

  logout(){
    sessionStorage.setItem("email","");
  }
}
