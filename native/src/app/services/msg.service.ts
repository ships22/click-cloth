import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class MsgService {

  message: string = '';

  sendMessage(message: string) {
    this.message = message;
    setTimeout(() => {
      this.clearMessage();
    }, 3000);
  }
  clearMessage() {
    this.message = '';
  }
  constructor() { }
}
