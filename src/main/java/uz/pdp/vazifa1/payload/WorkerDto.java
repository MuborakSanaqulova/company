package uz.pdp.vazifa1.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkerDto {
    @NotNull(message = "worker's name can not be empty")
    private String name;

    @NotNull(message = "worker's phoneNumber can not be empty")
    private String phoneNumber;

    @NotNull(message = "address can not be empty")
    private Integer addressId;

    @NotNull(message = "department can not be empty")
    private Integer departmentId;
}
