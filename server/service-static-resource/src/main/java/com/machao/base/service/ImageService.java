package com.machao.base.service;

import com.machao.base.model.mq.image.request.ImageDeleteRequest;
import com.machao.base.model.mq.image.request.ImageResizingRequest;
import com.machao.base.model.mq.image.response.ImageDeleteResponse;
import com.machao.base.model.mq.image.response.ImageResizingResponse;

public interface ImageService {

	ImageResizingResponse handle(ImageResizingRequest imageResizingRequest);
	
	ImageDeleteResponse handle(ImageDeleteRequest imageDeleteRequest);
	
}
