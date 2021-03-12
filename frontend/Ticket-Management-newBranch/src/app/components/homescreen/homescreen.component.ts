import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import {HttpClient} from '@angular/common/http';
import { Router } from '@angular/router';
import { identifierModuleUrl } from '@angular/compiler';


@Component({
  selector: 'app-homescreen',
  templateUrl: './homescreen.component.html',
  styleUrls: ['./homescreen.component.css']
})
export class HomescreenComponent implements OnInit {

   uuu="google.com";

  formGroup: FormGroup = new FormGroup({
   
    email: new FormControl('',[Validators.required,Validators.email]),
    password: new FormControl('',Validators.required),
    
  })

  constructor(private http:HttpClient, public router: Router) { }

  ngOnInit(): void {
  }

  signin(){


    let url="http://localhost:8080/user/login";


    sessionStorage.setItem("email",this.formGroup.value.email );

    this.http.post(url, {
      email:this.formGroup.value.email,
      password:this.formGroup.value.password,
    }).toPromise().then((data:any)=>{
      console.log(data)

      if(data.type=="ADMIN"){
        this.router.navigate(['/adminhome']);
      }
      else if(data.type=="USER"){
        this.router.navigate(['/userhome']);
      }
      else if(data.type=="MEMBER"){
        this.router.navigate(['/memberhome']);
      }

    });

    
    
  }


}
