package com.tamdao.spring_security_2.service.profile;

import com.tamdao.spring_security_2.entity.Profile;

public interface ProfileService {
    Profile  getProfile(int userId);
}
