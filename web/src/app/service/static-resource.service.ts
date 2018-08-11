import {Injectable} from '@angular/core';
import {FileUploader} from 'ng2-file-upload';
import {HttpClient} from '@angular/common/http';
import {Page} from '../model/Page';

const pictureUrl = '/api/static-resource/?type=image';
const audioUrl = '/api/static-resource/?type=audio';
const videoUrl = '/api/static-resource/?type=video';

@Injectable()
export class StaticResourceService {

  private imageUploader: FileUploader = new FileUploader({url: pictureUrl});
  private audioUploader: FileUploader = new FileUploader({url: audioUrl});
  private videoUploader: FileUploader = new FileUploader({url: videoUrl});

  constructor(private http: HttpClient) { }

  public getImageUploader() {
    return this.imageUploader;
  }

  public getAudioUploader() {
    return this.audioUploader;
  }

  public getVideoUploader() {
    return this.videoUploader;
  }

  public listImages(page: Page) {
    return this.http.get('/api/static-resource/type/image?page=' + (page.page - 1) + '&size=' + page.itemsPerPage);
  }

  public listVideos(page: Page) {
    return this.http.get('/api/static-resource/type/video?page=' + (page.page - 1) + '&size=' + page.itemsPerPage);
  }

  public listAudios(page: Page) {
    return this.http.get('/api/static-resource/type/audio?page=' + (page.page - 1) + '&size=' + page.itemsPerPage);
  }

  public deleteStaticResource(id: string) {
    return this.http.delete('/api/static-resource/' + id);
  }

}
