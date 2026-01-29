package com.lamb.chefaiserver.service;

/**
 * 内部图片生成抽象（开发环境可用 mock 实现）。
 */
public interface InternalImageService {
    /**
     * 根据提示词生成图片地址或标识。
     *
     * @param prompt 提示词
     * @return 图片地址或标识
     */
    String generateImage(String prompt);
}
