package uz.pdp.vazifa1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.vazifa1.entity.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {

    boolean existsByNameAndCompanyId(String name, Integer company_id);

    boolean existsByNameAndCompanyIdAndIdNot(String name, Integer company_id, Integer id);

}
