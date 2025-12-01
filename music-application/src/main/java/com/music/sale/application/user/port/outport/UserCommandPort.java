package com.music.sale.application.user.port.outport;

import com.music.sale.domain.user.User;
import com.music.sale.domain.user.UserSocial;
import com.music.sale.domain.user.UserTerms;

public interface UserCommandPort {
    User save(User user);

    UserTerms saveTerms(UserTerms userTerms);
}
