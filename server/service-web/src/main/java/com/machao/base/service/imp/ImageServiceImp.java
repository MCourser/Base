package com.machao.base.service.imp;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;

import com.machao.base.mq.QueueName;
import com.machao.base.mq.static_resource.image.request.ImageDeleteRequest;
import com.machao.base.mq.static_resource.image.request.ImageResizingRequest;
import com.machao.base.mq.static_resource.image.response.ImageDeleteResponse;
import com.machao.base.mq.static_resource.image.response.ImageResizingResponse;
import com.machao.base.service.ImageService;

@Service
public class ImageServiceImp implements ImageService {

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
