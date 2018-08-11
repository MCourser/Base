import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PictureStaticResourceComponent } from './picture-static-resource.component';

describe('AudioStaticResourceComponent', () => {
  let component: PictureStaticResourceComponent;
  let fixture: ComponentFixture<PictureStaticResourceComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PictureStaticResourceComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PictureStaticResourceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
