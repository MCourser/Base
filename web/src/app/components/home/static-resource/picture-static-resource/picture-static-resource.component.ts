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

  public previewImages: any = [];

  constructor(
    public router: Router,
    public toastyService: ToasterService,
    public userService: UserService,
    public staticResourceService: StaticResourceService
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

  public setPreview(image) {
    this.removePreview();
    this.previewImages.push('/api/static-resource/file/image/' + image.id);
  }
  public removePreview() {
    this.previewImages = [];
  }
  public isPreviewMode() {
    return this.previewImages.length > 0;
  }

  public togglePublic(id: string) {
    this.staticResourceService.togglePublic(id).toPromise().then(json => {
      this.list(this.page);
    });
  }
}
