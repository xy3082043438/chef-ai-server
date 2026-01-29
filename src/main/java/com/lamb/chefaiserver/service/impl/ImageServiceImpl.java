package com.lamb.chefaiserver.service.impl;

import com.lamb.chefaiserver.service.InternalImageService;
import org.springframework.ai.image.ImageModel;
import org.springframework.ai.image.ImagePrompt;
import org.springframework.ai.image.ImageResponse;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * 图片生成服务实现（基于 Spring AI ImageModel）。
 */
@Service
public class ImageServiceImpl implements InternalImageService {
    private final ImageModel imageModel;

    public ImageServiceImpl(ImageModel imageModel) {
        this.imageModel = imageModel;
    }

    /** {@inheritDoc} */
    @Override
    public String generateImage(String prompt) {
        try {
            ImageResponse response = imageModel.call(new ImagePrompt(prompt));
            if (response.getResult() == null) {
                return null;
            } else {
                response.getResult();
            }
            String url = response.getResult().getOutput().getUrl();
            return StringUtils.hasText(url) ? url : null;
        } catch (Exception e) {
            // 生成失败时返回空，避免阻塞主流程。
            return null;
        }
    }
}
