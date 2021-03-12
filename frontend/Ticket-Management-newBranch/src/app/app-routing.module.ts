import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomescreenComponent } from '../app/components/homescreen/homescreen.component';
import { SignupComponent } from './components/signup/signup.component';
import { AdminhomeComponent } from './components/adminhome/adminhome.component';
import { AdminticketComponent } from './components/adminticket/adminticket.component';
import { AdmingroupComponent } from './components/admingroup/admingroup.component';
import { AdmincategoryComponent } from './components/admincategory/admincategory.component';
import { AdminaddmemberComponent } from './components/adminaddmember/adminaddmember.component';
import { AdminviewmemberbygroupidComponent } from './components/adminviewmemberbygroupid/adminviewmemberbygroupid.component';
import { MemberhomeComponent } from './components/memberhome/memberhome.component';
import { UserhomeComponent } from './components/userhome/userhome.component';
import { UsercreateticketComponent } from './components/usercreateticket/usercreateticket.component';
const routes: Routes = [
  {path:'', component:HomescreenComponent},
  {path:'signup',component:SignupComponent},
  {path:'adminhome',component:AdminhomeComponent},
  {path:'adminticket',component:AdminticketComponent},
  {path:'admingroup',component:AdmingroupComponent},
  {path:'admincategory',component:AdmincategoryComponent},
  {path:'adminaddmember',component:AdminaddmemberComponent},
  {path:'adminviewmemberbygroupid',component:AdminviewmemberbygroupidComponent},
  {path:'memberhome',component:MemberhomeComponent},
  {path:'userhome',component:UserhomeComponent},
  {path:'usercreateticket',component:UsercreateticketComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

