package com.music.sale.application.like.port.inport;

import com.music.sale.application.like.dto.input.CreateLikeInput;
import com.music.sale.application.like.dto.input.DeleteLikeInput;
import com.music.sale.application.like.dto.output.CreateLikeOutput;

public interface LikeCommandUseCase {
    CreateLikeOutput createLike(CreateLikeInput input);

    void deleteLike(DeleteLikeInput input);
}



