import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {ToasterService} from 'angular2-toaster';
import {UserService} from '../../../../service/user.service';
import {StaticResourceService} from '../../../../service/static-resource.service';
import {FileUploader} from 'ng2-file-upload';
import {BaseStaticResourceComponent} from '../base-static-resource.component';
import {Page} from '../../../../model/Page';

@Component({
  selector: 'app-picture-static-resource',
  templateUrl: './picture-static-resource.component.html',
  styleUrls: ['../base-static-resource.component.css', './picture-static-resource.component.css']
})
export class PictureStaticResourceComponent extends BaseStaticResourceComponent implements OnInit {
  public page: Page = new Page(1, 12);
  public staticResourcePage: any = {};

  constructor(
    protected router: Router,
    protected toastyService: ToasterService,
    protected userService: UserService,
    protected staticResourceService: StaticResourceService
  ) {
    super(router, toastyService, userService, staticResourceService);
    this.getFileUploader().onCompleteAll = this.onCompleteAll.bind(this);
  }

  ngOnInit() {
    this.list(this.page);
  }

  protected onCompleteAll() {
    this.getFileUploader().clearQueue();
    this.list(this.page);
  }

  public getFileUploader(): FileUploader {
    return this.staticResourceService.getImageUploader();
  }

  public getAcceptContentType(): String {
    return 'image/*';
  }

  public list(page: Page) {
    this.page = page;
    this.staticResourceService.listImages(page).toPromise().then(json => {
      this.staticResourcePage = json;
    });
  }

  public delete(id: string) {
    this.staticResourceService.deleteStaticResource(id).toPromise().then(json => {
      this.list(this.page);
    });
  }

}
