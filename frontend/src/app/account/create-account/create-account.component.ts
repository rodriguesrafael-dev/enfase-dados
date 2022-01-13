import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-create-account',
  templateUrl: './create-account.component.html',
  styleUrls: ['./create-account.component.scss'],
})
export class CreateAccountComponent implements OnInit {
  account = {
    name: '',
    email: '',
    password: '',
  };

  constructor() {}

  ngOnInit(): void {}

  onSubmit() {

  }
}
