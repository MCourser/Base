import {AfterViewInit, Component, ElementRef, OnInit} from '@angular/core';
import {BaseStaticResourceComponent} from '../base-static-resource.component';
import {ToasterService} from 'angular2-toaster';
import {StaticResourceService} from '../../../../service/static-resource.service';
import {Router} from '@angular/router';
import {UserService} from '../../../../service/user.service';
import {Page} from '../../../../model/Page';
import {FileUploader} from 'ng2-file-upload';
import {tryCatch} from 'rxjs/internal/util/tryCatch';
import {ToasterType} from '../../../BaseCompoent';

@Component({
  selector: 'app-video-static-resource',
  templateUrl: './video-static-resource.component.html',
  styleUrls: ['../base-static-resource.component.css', './video-static-resource.component.css']
})
export class VideoStaticResourceComponent extends BaseStaticResourceComponent implements OnInit, AfterViewInit {
  public page: Page = new Page(0, 10);
  public staticResourcePage: any = {};

  public video: any;
  public player: any;

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

  ngAfterViewInit() {
    this.player = videojs('videoElement');
  }

  public getAcceptContentType() {
    return 'video/*';
  }

  public getFileUploader(): FileUploader {
    return this.staticResourceService.getVideoUploader();
  }

  protected onCompleteAll() {
    this.getFileUploader().clearQueue();
    this.list(this.page);
  }

  public list(page: Page) {
    this.page = page;
    this.staticResourceService.listVideos(this.page).toPromise().then(json => {
      this.staticResourcePage = json;
    });
  }

  public delete(id: string) {
    this.staticResourceService.deleteStaticResource(id).toPromise().then(json => {
      this.list(this.page);
    });
  }

  public play(video: any) {
    try {
      this.video = video;
      this.player.src({
        src: '/api/static-resource/file/video/' + video.id,
        type: 'application/x-mpegURL'
      });
      this.player.play();
    } catch (e) {
      this.showToasty(ToasterType.error, '错误', '视频播放失败，请稍后重试。');
    }
  }

  public stop() {
    this.video = null;
    this.player.dispose();
  }

}
