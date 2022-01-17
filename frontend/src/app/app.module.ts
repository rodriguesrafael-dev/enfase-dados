import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { MatToolbarModule } from '@angular/material/toolbar';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { CreateAccountComponent } from './account/create-account/create-account.component';
import { LoginComponent } from './account/login/login.component';
import { AccountService } from './account/shared/account.service';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AuthenticationComponent } from './layout/authentication/authentication.component';
import { ChartsFormComponent } from './layout/charts-form/charts-form.component';
import { HomeComponent } from './layout/home/home.component';
import { ChartsFormAdminComponent } from './layout/charts-form-admin/charts-form-admin.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    ChartsFormComponent,
    AuthenticationComponent,
    LoginComponent,
    CreateAccountComponent,
    ChartsFormAdminComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatToolbarModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [AccountService],
  bootstrap: [AppComponent]
})
export class AppModule { }
