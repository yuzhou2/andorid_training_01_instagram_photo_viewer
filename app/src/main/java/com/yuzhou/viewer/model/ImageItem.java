package com.yuzhou.viewer.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by yuzhou on 2015/07/26.
 */
@EqualsAndHashCode
@ToString
public class ImageItem
{
    @Getter
    @Setter
    private String type;
    @Getter
    @Setter
    private long createdTime;
    @Getter
    @Setter
    private Image thumbnail;
    @Getter
    @Setter
    private User user;
    @Getter
    @Setter
    private Caption caption;
    @Getter
    @Setter
    private Likes likes;
}
