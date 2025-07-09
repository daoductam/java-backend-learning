package com.tamdao.spring_security_2.dto.profile;

import com.tamdao.spring_security_2.entity.Profile;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetProfileResponse {
    Profile profile;
}
