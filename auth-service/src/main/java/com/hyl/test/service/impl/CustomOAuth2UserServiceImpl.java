package com.hyl.test.service.impl;

import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class CustomOAuth2UserServiceImpl extends DefaultOAuth2UserService {

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest)  {
        OAuth2User user = super.loadUser(userRequest);

        // 在此处可以保存用户信息到数据库
        // ...

        return user;
    }
}
