package com.superarilo.utils;

public class InputCheck {

    public static final String passwordRegex = "^(?![A-Za-z0-9]+$)(?![a-z0-9#?!@$%^&*-.]+$)(?![A-Za-z#?!@$%^&*-.]+$)(?![A-Z0-9#?!@$%^&*-.]+$)[a-zA-Z0-9#?!@$%^&*-.]{8,16}$";
    public static final String nickNameRegex = "^[\\u4E00-\\u9FA5A-Za-z0-9_]{2,6}";
    public static final String emailRegex = "^(\\w+([-.][A-Za-z0-9]+)*){3,18}@\\w+([-.][A-Za-z0-9]+)*\\.\\w+([-.][A-Za-z0-9]+)*$";
    public static final String tokenSecret = "fkoWzkHn";
    public static final long tokenExpireTime = 3000;
    public static final String defaultAvatar = "custom-property.default-avatar";
    public static final String imagePath = "D:/TempCenter/imageBlog/";
    public static final String[] imageSuffix = {"PNG","JPG","JPEG","BMP","GIF","SVG"};
}
