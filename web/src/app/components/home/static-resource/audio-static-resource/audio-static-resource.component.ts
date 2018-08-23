import {Component, OnDestroy, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {ToasterService} from 'angular2-toaster';
import {UserService} from '../../../../service/user.service';
import {StaticResourceService} from '../../../../service/static-resource.service';
import {FileUploader} from 'ng2-file-upload';
import {BaseStaticResourceComponent} from '../base-static-resource.component';
import {Page} from '../../../../model/Page';
import {MusicPlayerService} from '../../../../service/music-player.service';

@Component({
  selector: 'app-audio-static-resource',
  templateUrl: './audio-static-resource.component.html',
  styleUrls: ['../base-static-resource.component.css', './audio-static-resource.component.css']
})
export class AudioStaticResourceComponent extends BaseStaticResourceComponent implements OnInit, OnDestroy {
  public page: Page = new Page(0, 10);
  public staticResourcePage: any = {};

  constructor(
    public router: Router,
    public toastyService: ToasterService,
    public userService: UserService,
    public staticResourceService: StaticResourceService,
    public musicPlayerService: MusicPlayerService
  ) {
    super(router, toastyService, userService, staticResourceService);
    this.getFileUploader().onCompleteAll = this.onCompleteAll.bind(this);
  }

  ngOnInit() {
    this.musicPlayerService.enableMusicMode();
    this.list(this.page);
  }
  ngOnDestroy() {
    this.musicPlayerService.disableMusicMode();
  }

  protected onCompleteAll() {
    this.getFileUploader().clearQueue();
    this.list(this.page);
  }

  public getFileUploader(): FileUploader {
    return this.staticResourceService.getAudioUploader();
  }

  public getAcceptContentType(): String {
    return 'audio/*';
  }


  public musicPlayer() {
    return this.musicPlayerService;
  }

  public list(page: Page) {
    this.page = page;
    this.staticResourceService.listAudios(this.page).toPromise().then(json => {
      this.staticResourcePage = json;
      console.log(json);
    });
  }

  public delete(id: string) {
    this.staticResourceService.deleteStaticResource(id).toPromise().then(json => {
      this.list(this.page);
    });
  }

}
