package uz.pdp.vazifa1.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentDto {

    @NotNull(message = "department name can not be empty")
    private String name;

    @NotNull(message = "company can not be empty")
    private Integer companyId;

}
