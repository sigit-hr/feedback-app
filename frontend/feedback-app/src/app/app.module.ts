import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FeedbackFormComponent } from './feedback-form/feedback-form.component';
import { HomeComponent } from './home/home.component';
import { FeedbackAdministrationComponent } from './feedback-administration/feedback-administration.component';

@NgModule({
  declarations: [
    AppComponent,
    FeedbackFormComponent,
    HomeComponent,
    FeedbackAdministrationComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {}
