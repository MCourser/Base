import { Injectable } from '@angular/core';

@Injectable()
export class MusicPlayerService {
  private musicMode = false;

  private audioElement: HTMLAudioElement;
  private hls: any;
  private playing = false;
  private _currentTime = 0;
  private _duration = 0;
  private _volume = 1;

  private timer;

  private audio: any;

  constructor() {
    this.audioElement = document.createElement('audio');
    this.audioElement.autoplay = false;
    this.audioElement.onplay = this.onPlay.bind(this);
    this.audioElement.onended = this.onEnded.bind(this);
    this.audioElement.onabort = this.onAbort.bind(this);
    this.audioElement.onpause = this.onPaste.bind(this);

    this.timer = setInterval(() => {
      this._currentTime = this.audioElement.currentTime ? this.audioElement.currentTime : 0;
      this._duration = this.audioElement.duration ? this.audioElement.duration : 0;
      this._volume = this.audioElement.volume;
    }, 500);
  }

  public enableMusicMode() {
    this.musicMode = true;
  }

  public disableMusicMode() {
    this.musicMode = false;
    this.pause();
  }

  public isMusicMode() {
    return this.musicMode;
  }

  public getAudio() {
    return this.audio;
  }

  public isPause() {
    return !this.playing;
  }

  public isPlaying() {
    return this.playing;
  }

  public progress() {
    return this._currentTime / this._duration * 100;
  }

  public currentTime() {
    return this._currentTime;
  }

  public duration() {
    return this._duration;
  }

  public play() {
    this.audioElement.play();
  }

  public pause() {
    this.audioElement.pause();
  }

  public volume() {
    return this._volume * 100;
  }

  public seek(percent: number) {
    if (this.audio) {
      const dstTime = this._duration * percent;
      this.audioElement.currentTime = dstTime;
    }
  }

  public toggle() {
    if (this.audio) {
      if (this.isPlaying()) {
        this.pause();
      } else {
        this.play();
        if (!this.hls) {
          this.switch(this.audio);
        }
      }
    }
  }

  public switch(audio: any) {
    if (this.audio && this.audio.id === audio.id) {
      return;
    }
    this.audio = audio;

    if (Hls.isSupported()) {
      this.hls = new Hls();
      this.hls.loadSource('/api/static-resource/file/audio/' + audio.id);
      this.hls.attachMedia(this.audioElement);
      this.hls.on(Hls.Events.MANIFEST_PARSED, () => {
        console.log('>>>>>>>>>>>>>>>audio loadedmetadata');
        this.play();
      });
    } else if (
      this.audioElement.canPlayType('application/vnd.apple.mpegurl')) {
      this.audioElement.src = '/api/static-resource/file/audio/' + audio.id;
      this.audioElement.addEventListener('loadedmetadata', () => {
        console.log('>>>>>>>>>>>>>>>audio loadedmetadata');
        this.play();
      });
    } else {
      console.log('Hls is Not Supported');
    }
  }

  public onPlay() {
    this.playing = true;
  }

  public onEnded() {
    this.seek(0);
    console.log('>>>>>>>>>>>>>onEnded');
  }

  public onAbort() {
    console.log('>>>>>>>>>>>>>onAbort');
  }

  public onPaste() {
    this.playing = false;
  }

}
