package com.machao.base.statis_resource.service;

import com.machao.base.mq.static_resource.video.request.VideoConvertRequest;
import com.machao.base.mq.static_resource.video.request.VideoDeleteRequest;
import com.machao.base.mq.static_resource.video.request.VideoPlayListRequest;
import com.machao.base.mq.static_resource.video.response.VideoDeleteResponse;
import com.machao.base.mq.static_resource.video.response.VideoPlayListResponse;

public interface VideoService {
	
	void convert(VideoConvertRequest videoConvertRequest);
	
	VideoDeleteResponse handle(VideoDeleteRequest videoDeleteRequest);
	
	VideoPlayListResponse handle(VideoPlayListRequest videoPlayListRequest);

}
