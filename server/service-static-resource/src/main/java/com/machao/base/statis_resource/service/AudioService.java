package com.machao.base.statis_resource.service;

import com.machao.base.mq.static_resource.audio.request.AudioConvertRequest;
import com.machao.base.mq.static_resource.audio.request.AudioDeleteRequest;
import com.machao.base.mq.static_resource.audio.request.AudioPlayListRequest;
import com.machao.base.mq.static_resource.audio.response.AudioDeleteResponse;
import com.machao.base.mq.static_resource.audio.response.AudioPlayListResponse;

public interface AudioService {
	
	void convert(AudioConvertRequest audioConvertRequest);
	
	AudioDeleteResponse handle(AudioDeleteRequest audioDeleteRequest);
	
	AudioPlayListResponse handle(AudioPlayListRequest audioPlayListRequest);

}
