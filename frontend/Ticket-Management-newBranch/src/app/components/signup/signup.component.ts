import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import {MatSnackBar} from '@angular/material/snack-bar';
import {HttpClient} from '@angular/common/http';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {

  formGroup: FormGroup = new FormGroup({
    name: new FormControl('',Validators.required),
    email: new FormControl('',[Validators.required,Validators.email]),
    password: new FormControl('',[Validators.required,Validators.minLength(6),Validators.pattern('[a-zA-Z0-9]*')]),
    usertype: new FormControl('',Validators.required)
  })


  constructor(private _snackBar: MatSnackBar, private http:HttpClient) { }

  ngOnInit(): void {
  }

  


  show(){

    let url="http://localhost:8080/user/register";

    this.http.post(url, {
      name:this.formGroup.value.name,
      email:this.formGroup.value.email,
      password:this.formGroup.value.password,
      type:this.formGroup.value.usertype
    }).toPromise().then((data:any)=>{
      console.log(data)
    });


    this._snackBar.open('Sign Up Successful!','Ok',{duration:3000});
  }

}
