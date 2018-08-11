package com.machao.base.service.imp;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.machao.base.mq.QueueName;
import com.machao.base.mq.static_resource.audio.request.AudioConvertRequest;
import com.machao.base.mq.static_resource.audio.request.AudioDeleteRequest;
import com.machao.base.mq.static_resource.audio.request.AudioPlayListRequest;
import com.machao.base.mq.static_resource.audio.response.AudioDeleteResponse;
import com.machao.base.mq.static_resource.audio.response.AudioPlayListResponse;
import com.machao.base.service.AudioService;

@Service
public class AudioServiceImp implements AudioService {
	
	@Autowired
	private AmqpTemplate rabbitTemplate;

	@Async
	@Override
	public void convert(AudioConvertRequest audioConvertRequest) {
		this.rabbitTemplate.convertAndSend(QueueName.AudioConvert, audioConvertRequest);
	}

	@Override
	public AudioDeleteResponse delete(AudioDeleteRequest audioDeleteRequest) {
		return this.rabbitTemplate.convertSendAndReceiveAsType(QueueName.AudioDelete, audioDeleteRequest, ParameterizedTypeReference.forType(AudioDeleteResponse.class));		
	}

	@Override
	public AudioPlayListResponse palylist(AudioPlayListRequest audioPlayListRequest) {
		return this.rabbitTemplate.convertSendAndReceiveAsType(QueueName.AudioPlayList, audioPlayListRequest, ParameterizedTypeReference.forType(AudioPlayListResponse.class));		
	}
}
