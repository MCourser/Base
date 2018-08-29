package com.machao.base.service;

import com.machao.base.model.mq.image.request.ImageDeleteRequest;
import com.machao.base.model.mq.image.request.ImageResizingRequest;
import com.machao.base.model.mq.image.response.ImageDeleteResponse;
import com.machao.base.model.mq.image.response.ImageResizingResponse;

public interface ImageMessageService {
	ImageResizingResponse resizing(ImageResizingRequest imageResizingRequest);
	ImageDeleteResponse delete(ImageDeleteRequest imageDeleteRequest);
}
