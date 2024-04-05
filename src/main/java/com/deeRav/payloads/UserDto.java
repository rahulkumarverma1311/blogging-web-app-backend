package com.deeRav.payloads;


//import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Integer id;
    @NotEmpty
    @Size (min = 3, message = "Username Should Contain Atleast Three Charactor")
    private String name;
    @Email(message = "Given Email Is Not Valid")
    private String email;
    @NotEmpty
    @Size(min = 4 , max = 10, message = "Password Should Contain Min Three Charactor and Max Ten Charactor ")
    private String password;
    @NotEmpty
    private String about;
}
