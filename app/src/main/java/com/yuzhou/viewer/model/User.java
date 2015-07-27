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
public class User
{
    @Getter
    @Setter
    private String userName;
    @Getter
    @Setter
    private String fullName;
    @Getter
    @Setter
    private String pictureUrl;
}
