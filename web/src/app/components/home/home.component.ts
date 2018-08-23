import {AfterViewInit, Component, HostListener, OnDestroy, OnInit, ViewChild} from '@angular/core';
import {Router} from '@angular/router';
import {BaseCompoent} from '../BaseCompoent';
import {UserService} from '../../service/user.service';
import {ToasterService} from 'angular2-toaster';
import {NbMediaBreakpointsService, NbSidebarService} from '@nebular/theme';
import {MusicPlayerService} from '../../service/music-player.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent extends BaseCompoent implements OnInit, OnDestroy {
  public menuItems = [
    {
      title: '用户管理',
      expanded: true,
      icon: 'ion ion-md-contact',
      children: [
        {
          title: '权限',
          link: ['/home/permission'],
        },
        {
          title: '角色',
          link: ['/home/role/list'],
        },
        {
          title: '用户',
          link: ['/home/user/list'],
        },
      ],
    },
    {
      title: '多媒体',
      expanded: true,
      icon: 'ion ion-md-document',
      children: [
        {
          title: '图片',
          link: ['/home/static-resource/picture'],
        },
        {
          title: '音乐',
          link: ['/home/static-resource/audio'],
        },
        {
          title: '视频',
          link: ['/home/static-resource/video'],
        },
      ],
    },
  ];

  public userItems = [
    {
      title: '个人信息',
      link: ['/home/user/profile'],
    },
    {
      title: '退出',
      link: ['/logout'],
    }
  ];

  private alive = true;

  public isShowSidebarToggleButton = false;
  public sidebarState = ''; // `expanded`|`collapsed`|`compacted`

  constructor(
    public router: Router,
    public toastyService: ToasterService,
    public userService: UserService,
    public mediaBreakpointsService: NbMediaBreakpointsService,
    public sidebarService: NbSidebarService,
    public musicPlayerService: MusicPlayerService
  ) {
    super(router, toastyService, userService);
    this.sizing(window.innerWidth, window.innerHeight);
  }

  public ngOnInit() {
    super.ngOnInit();
  }

  public ngOnDestroy() {
    this.alive = false;
  }

  @HostListener('window:resize', ['$event'])
  public onResize(event) {
    this.sizing(event.target.innerWidth, event.target.innerHeight);
  }

  private sizing(width: number, height: number) {
    const minMediaBreak = this.mediaBreakpointsService.getByName('lg');
    if (width < minMediaBreak.width) {
      this.sidebarService.collapse('home-sidebar');
      this.isShowSidebarToggleButton = true;
      this.sidebarState = 'collapsed';
    } else {
      this.sidebarService.expand('home-sidebar');
      this.isShowSidebarToggleButton = false;
      this.sidebarState = 'expanded';
    }
  }

  public toggleSidebar() {
    this.isShowSidebarToggleButton = true;
    if (this.sidebarState === 'collapsed') {
      this.sidebarService.expand('home-sidebar');
      this.sidebarState = 'expanded';
    } else {
      this.sidebarService.collapse('home-sidebar');
      this.sidebarState = 'collapsed';
    }
  }

  public musicPlayer() {
    return this.musicPlayerService;
  }

  public onMusicProgressBarClick(event) {
    console.log(event);
  }

  public getCurrentYear() {
    return new Date().getFullYear();
  }

}
