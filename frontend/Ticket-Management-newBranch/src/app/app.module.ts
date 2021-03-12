import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { HomescreenComponent } from './components/homescreen/homescreen.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FlexLayoutModule } from '@angular/flex-layout';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatInputModule} from '@angular/material/input';
import {MatButtonModule} from '@angular/material/button';
import {MatCardModule} from '@angular/material/card';
import {MatToolbarModule} from '@angular/material/toolbar';
import { SignupComponent } from './components/signup/signup.component';
import {MatDividerModule} from '@angular/material/divider';
import {MatSelectModule} from '@angular/material/select';
import { AdminhomeComponent } from './components/adminhome/adminhome.component';
import { AdminticketComponent } from './components/adminticket/adminticket.component';
import { AdmingroupComponent } from './components/admingroup/admingroup.component';
import { AdmincategoryComponent } from './components/admincategory/admincategory.component';
import {MatTableModule} from '@angular/material/table';
import { ReactiveFormsModule } from '@angular/forms';
import { FormsModule } from '@angular/forms';
import {MatDialogModule} from '@angular/material/dialog';
import { AdminviewticketComponent } from './components/adminviewticket/adminviewticket.component';
import {MatSnackBarModule} from '@angular/material/snack-bar';
import {MatListModule} from '@angular/material/list';
import { AdminviewgroupComponent } from './components/adminviewgroup/adminviewgroup.component';
import { AdminaddmemberComponent } from './components/adminaddmember/adminaddmember.component';
import { HttpClientModule } from '@angular/common/http';
import { AdminviewmemberbygroupidComponent } from './components/adminviewmemberbygroupid/adminviewmemberbygroupid.component';
import { MemberhomeComponent } from './components/memberhome/memberhome.component';
import { UserhomeComponent } from './components/userhome/userhome.component';
import { UsercreateticketComponent } from './components/usercreateticket/usercreateticket.component';

@NgModule({
  declarations: [
    AppComponent,
    HomescreenComponent,
    SignupComponent,
    AdminhomeComponent,
    AdminticketComponent,
    AdmingroupComponent,
    AdmincategoryComponent,
    AdminviewticketComponent,
    AdminviewgroupComponent,
    AdminaddmemberComponent,
    AdminviewmemberbygroupidComponent,
    MemberhomeComponent,
    UserhomeComponent,
    UsercreateticketComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    NgbModule,
    BrowserAnimationsModule,
    FlexLayoutModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatCardModule,
    MatToolbarModule,
    MatDividerModule,
    MatSelectModule,
    MatTableModule,
    ReactiveFormsModule,
    FormsModule,
    MatDialogModule,
    MatSnackBarModule,
    MatListModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
