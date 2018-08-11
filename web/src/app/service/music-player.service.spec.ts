import { TestBed, inject } from '@angular/core/testing';

import { MusicPlayerService } from './music-player.service';

describe('MusicPlayerService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [MusicPlayerService]
    });
  });

  it('should be created', inject([MusicPlayerService], (service: MusicPlayerService) => {
    expect(service).toBeTruthy();
  }));
});
