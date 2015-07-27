package com.yuzhou.viewer.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by yuzhou on 2015/07/27.
 */
@EqualsAndHashCode
@ToString
public class Image
{
    @Getter
    @Setter
    private String url;
    @Getter
    @Setter
    private int height;
    @Getter
    @Setter
    private int width;
}
