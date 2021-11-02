package uz.pdp.vazifa1.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyDto {

    @NotNull(message = "corpName can not be empty")
    private String corpName;

    @NotNull(message = "directorName can not be empty")
    private String directorName;

    @NotNull(message = "address can not be empty")
    private Integer addressId;
}
