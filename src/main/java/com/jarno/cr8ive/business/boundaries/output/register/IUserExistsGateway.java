package com.jarno.cr8ive.business.boundaries.output.register;

public interface IUserExistsGateway {
    boolean existsById(String id);
}
