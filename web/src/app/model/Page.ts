import {PageChangedEvent} from 'ngx-bootstrap/pagination/pagination.component';

export class Page implements PageChangedEvent {
  public page: number;
  public itemsPerPage: number;

  constructor(page: number, itemsPerPage: number) {
    this.page = page;
    this.itemsPerPage = itemsPerPage;
  }
}
