package com.tamdao.spring_security_2.service.profile;

import com.tamdao.spring_security_2.entity.Profile;
import com.tamdao.spring_security_2.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProfileServiceImpl implements ProfileService {
    @Autowired
    ProfileRepository profileRepository;

    @Override
    public Profile getProfile(int userId) {
        Optional<Profile> optionalProfile = profileRepository.findById(userId);
        // implement business logic

        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getProfile'");
    }
}
