package com.machao.base.service.imp;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.machao.base.model.mq.QueueName;
import com.machao.base.model.mq.video.request.VideoConvertRequest;
import com.machao.base.model.mq.video.request.VideoDeleteRequest;
import com.machao.base.model.mq.video.request.VideoPlayListRequest;
import com.machao.base.model.mq.video.response.VideoDeleteResponse;
import com.machao.base.model.mq.video.response.VideoPlayListResponse;
import com.machao.base.service.VideoMessageService;

@Service
public class VideoMessageServiceImp implements VideoMessageService {
	
	@Autowired
	private AmqpTemplate rabbitTemplate;

	@Async
	@Override
	public void convert(VideoConvertRequest videoConvertRequest) {
		this.rabbitTemplate.convertAndSend(QueueName.VideoConvert, videoConvertRequest);
	}

	@Override
	public VideoDeleteResponse delete(VideoDeleteRequest videoDeleteRequest) {
		return this.rabbitTemplate.convertSendAndReceiveAsType(QueueName.VideoDelete, videoDeleteRequest, ParameterizedTypeReference.forType(VideoDeleteResponse.class));		
	}

	@Override
	public VideoPlayListResponse palylist(VideoPlayListRequest videoPlayListRequest) {
		return this.rabbitTemplate.convertSendAndReceiveAsType(QueueName.VideoPlayList, videoPlayListRequest, ParameterizedTypeReference.forType(VideoPlayListResponse.class));		
	}
}
