import { environment } from './../../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class AccountService {

  constructor(private http: HttpClient) {}

  async login(user: any) {
    const result = await this.http.post<any>(`${environment.api}/login`, user).toPromise();

    if (result && result.access_token) {
      window.localStorage.setItem('token', result.access_token);
      return true;
    }
    return false;
  }

  async createAccount(account: any) {
    const result = await this.http.post<any>(`${environment.api}/api/user/saveUser`, account).toPromise();
    return result;
  }

}
