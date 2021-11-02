package uz.pdp.vazifa1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.vazifa1.common.ApiResponse;
import uz.pdp.vazifa1.entity.Company;
import uz.pdp.vazifa1.entity.Department;
import uz.pdp.vazifa1.payload.DepartmentDto;
import uz.pdp.vazifa1.repository.CompanyRepository;
import uz.pdp.vazifa1.repository.DepartmentRepository;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    DepartmentRepository departmentRepository;

    public List<Department> getDepartments() {
        return departmentRepository.findAll();
    }

    public Department getDepartment(Integer id) {
        Optional<Department> byId = departmentRepository.findById(id);
        if (byId.isPresent())
            return byId.get();
        return null;
    }

    public ApiResponse addDepartment(DepartmentDto departmentDto) {

        Optional<Company> optionalCompany = companyRepository.findById(departmentDto.getCompanyId());
        if (!optionalCompany.isPresent())
            return new ApiResponse("company not found ", false);

        boolean byNameAndCompanyId = departmentRepository.existsByNameAndCompanyId(departmentDto.getName(), departmentDto.getCompanyId());
        if (byNameAndCompanyId)
            return new ApiResponse("already exist department", false);

        Department department = new Department();
        department.setCompany(optionalCompany.get());
        department.setName(departmentDto.getName());

        departmentRepository.save(department);
        return new ApiResponse("department added", true);
    }

    public ApiResponse editDepartment(Integer id, DepartmentDto departmentDto) {
        Optional<Department> optionalDepartment = departmentRepository.findById(id);
        if (!optionalDepartment.isPresent())
            return new ApiResponse("department not found", false);

        Optional<Company> optionalCompany = companyRepository.findById(departmentDto.getCompanyId());
        if (!optionalCompany.isPresent())
            return new ApiResponse("company not found ", false);

        boolean idNot = departmentRepository.existsByNameAndCompanyIdAndIdNot(departmentDto.getName(), departmentDto.getCompanyId(), id);
        if (idNot)
            return new ApiResponse("this department already exist", false);

        Department department = new Department();
        department.setId(id);
        department.setCompany(optionalCompany.get());
        department.setName(departmentDto.getName());

        departmentRepository.save(department);
        return new ApiResponse("department edited", true);

    }

    public ApiResponse deleteDepartment(Integer id) {
        Optional<Department> optionalDepartment = departmentRepository.findById(id);
        if (!optionalDepartment.isPresent())
            return new ApiResponse("department not found", false);

        departmentRepository.deleteById(id);
        return new ApiResponse("successfully deleted", true);
    }

}
