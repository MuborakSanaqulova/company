package uz.pdp.vazifa1.payload;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDto {

    @NotNull(message = "street can not be empty")
    private String street;

    @NotNull(message = "homeNumber can not be empty")
    private String homeNumber;

}
