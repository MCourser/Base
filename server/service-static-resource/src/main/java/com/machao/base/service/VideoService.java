package com.machao.base.service;

import com.machao.base.model.mq.video.request.VideoConvertRequest;
import com.machao.base.model.mq.video.request.VideoDeleteRequest;
import com.machao.base.model.mq.video.request.VideoPlayListRequest;
import com.machao.base.model.mq.video.response.VideoDeleteResponse;
import com.machao.base.model.mq.video.response.VideoPlayListResponse;

public interface VideoService {
	
	void convert(VideoConvertRequest videoConvertRequest);
	
	VideoDeleteResponse handle(VideoDeleteRequest videoDeleteRequest);
	
	VideoPlayListResponse handle(VideoPlayListRequest videoPlayListRequest);

}
