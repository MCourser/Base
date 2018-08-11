import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AudioStaticResourceComponent } from './audio-static-resource.component';

describe('AudioStaticResourceComponent', () => {
  let component: AudioStaticResourceComponent;
  let fixture: ComponentFixture<AudioStaticResourceComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AudioStaticResourceComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AudioStaticResourceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
