import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs/Observable';

import { ListService } from '../list/list.service';
import { List } from '../list/list';

@Component({
  selector: 'app-board',
  templateUrl: './board.component.html',
  styleUrls: ['./board.component.css']
})
export class BoardComponent implements OnInit {

  lists: List[];

  constructor(
    private listService: ListService) { }

  ngOnInit() {

    this.listService.getAll().then(lists => this.lists = lists);

    console.log(this.lists);
  }

}
