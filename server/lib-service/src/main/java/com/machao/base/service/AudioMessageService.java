package com.machao.base.service;

import com.machao.base.model.mq.audio.request.AudioConvertRequest;
import com.machao.base.model.mq.audio.request.AudioDeleteRequest;
import com.machao.base.model.mq.audio.request.AudioPlayListRequest;
import com.machao.base.model.mq.audio.response.AudioDeleteResponse;
import com.machao.base.model.mq.audio.response.AudioPlayListResponse;

public interface AudioMessageService {
	void convert(AudioConvertRequest audioConvertRequest);
	
	AudioDeleteResponse delete(AudioDeleteRequest audioDeleteRequest);

	AudioPlayListResponse palylist(AudioPlayListRequest audioPlayListRequest);
}
