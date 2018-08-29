package com.machao.base.service;

import com.machao.base.model.mq.audio.request.AudioConvertRequest;
import com.machao.base.model.mq.audio.request.AudioDeleteRequest;
import com.machao.base.model.mq.audio.request.AudioPlayListRequest;
import com.machao.base.model.mq.audio.response.AudioDeleteResponse;
import com.machao.base.model.mq.audio.response.AudioPlayListResponse;

public interface AudioService {
	
	void convert(AudioConvertRequest audioConvertRequest);
	
	AudioDeleteResponse handle(AudioDeleteRequest audioDeleteRequest);
	
	AudioPlayListResponse handle(AudioPlayListRequest audioPlayListRequest);

}
