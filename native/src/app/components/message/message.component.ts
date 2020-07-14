import { Component, OnInit } from '@angular/core';
import { MsgService } from 'src/app/services/msg.service';

@Component({
  selector: 'app-message',
  templateUrl: './message.component.html',
  styleUrls: ['./message.component.scss'],
})
export class MessageComponent implements OnInit {

  constructor(public messageService: MsgService) { }

  ngOnInit() {}

}
