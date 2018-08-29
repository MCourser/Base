package com.machao.base.service.imp;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;

import com.machao.base.model.mq.QueueName;
import com.machao.base.model.mq.image.request.ImageDeleteRequest;
import com.machao.base.model.mq.image.request.ImageResizingRequest;
import com.machao.base.model.mq.image.response.ImageDeleteResponse;
import com.machao.base.model.mq.image.response.ImageResizingResponse;
import com.machao.base.service.ImageMessageService;

@Service
public class ImageMessageServiceImp implements ImageMessageService {

	@Autowired
	private AmqpTemplate rabbitTemplate;
	
	@Override
	public ImageResizingResponse resizing(ImageResizingRequest imageResizingRequest) {
		return this.rabbitTemplate.convertSendAndReceiveAsType(QueueName.ImageResizing, imageResizingRequest, ParameterizedTypeReference.forType(ImageResizingResponse.class));
	}

	@Override
	public ImageDeleteResponse delete(ImageDeleteRequest imageDeleteRequest) {
		return this.rabbitTemplate.convertSendAndReceiveAsType(QueueName.ImageDelete, imageDeleteRequest, ParameterizedTypeReference.forType(ImageDeleteResponse.class));
	}

}
