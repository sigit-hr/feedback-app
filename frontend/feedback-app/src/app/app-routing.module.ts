import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { FeedbackFormComponent } from './feedback-form/feedback-form.component';
import { FeedbackAdministrationComponent } from './feedback-administration/feedback-administration.component';
import {HomeComponent} from "./home/home.component";

const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'feedback', component: FeedbackFormComponent },
  { path: 'admin/feedback', component: FeedbackAdministrationComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}
