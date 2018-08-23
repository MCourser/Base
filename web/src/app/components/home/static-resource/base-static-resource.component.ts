import {OnInit} from '@angular/core';
import {BaseCompoent} from '../../BaseCompoent';
import {Router} from '@angular/router';
import {ToasterService} from 'angular2-toaster';
import {UserService} from '../../../service/user.service';
import {FileUploader} from 'ng2-file-upload';
import {StaticResourceService} from '../../../service/static-resource.service';

export abstract class BaseStaticResourceComponent extends BaseCompoent implements OnInit {

  public hasBaseDropZoneOver = false;
  public hasAnotherDropZoneOver = false;

  constructor(
    public router: Router,
    public toastyService: ToasterService,
    public userService: UserService,
    public staticResourceService: StaticResourceService
  ) {
    super(router, toastyService, userService);
  }

  public fileOverBase(e: any) {
    this.hasBaseDropZoneOver = e;
  }

  public fileOverAnother(e: any) {
    this.hasAnotherDropZoneOver = e;
  }

  public abstract getFileUploader(): FileUploader;

  public abstract getAcceptContentType(): String;


}
