package com.machao.base.service;

import com.machao.base.mq.static_resource.image.request.ImageDeleteRequest;
import com.machao.base.mq.static_resource.image.request.ImageResizingRequest;
import com.machao.base.mq.static_resource.image.response.ImageDeleteResponse;
import com.machao.base.mq.static_resource.image.response.ImageResizingResponse;

public interface ImageService {
	ImageResizingResponse resizing(ImageResizingRequest imageResizingRequest);
	ImageDeleteResponse delete(ImageDeleteRequest imageDeleteRequest);
}
