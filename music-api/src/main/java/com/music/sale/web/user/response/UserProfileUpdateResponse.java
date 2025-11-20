package com.music.sale.web.user.response;

import java.util.List;

public record UserProfileUpdateResponse(
        Boolean success,
        String message,
        List<String> updatedFields) {}

