package com.machao.base.service;

import com.machao.base.mq.static_resource.video.request.VideoConvertRequest;
import com.machao.base.mq.static_resource.video.request.VideoDeleteRequest;
import com.machao.base.mq.static_resource.video.request.VideoPlayListRequest;
import com.machao.base.mq.static_resource.video.response.VideoDeleteResponse;
import com.machao.base.mq.static_resource.video.response.VideoPlayListResponse;

public interface VideoService {
	void convert(VideoConvertRequest videoConvertRequest);
	
	VideoDeleteResponse delete(VideoDeleteRequest videoDeleteRequest);

	VideoPlayListResponse palylist(VideoPlayListRequest videoPlayListRequest);
}
