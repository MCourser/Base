import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { VideoStaticResourceComponent } from './video-static-resource.component';

describe('VideoStaticResourceComponent', () => {
  let component: VideoStaticResourceComponent;
  let fixture: ComponentFixture<VideoStaticResourceComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ VideoStaticResourceComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(VideoStaticResourceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
