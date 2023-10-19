package com.jarno.cr8ive.business.boundaries.output.register;

import com.jarno.cr8ive.domain.Post;

public interface IPostRegisterGateway extends IPostExistsGateway{
    void save(Post post);
}
