package com.jarno.cr8ive.controller.response.user;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserResponse {
    @NotBlank
    @NotNull
    @Min(1)
    @Getter
    @Setter
    private long id;
}
