package com.deeRav.payloads;


//import jakarta.validation.constraints.NotBlank;
//import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {

    private Integer categoryId;
    @NotBlank
    @Size(min = 3, message = "Category Title should be atleast 3 charactor")
    private String categoryTitle;
    @NotBlank
    @Size(min= 10, message = "the min size of  description should be 10 charactor")
    private String categoryDescription;

}
