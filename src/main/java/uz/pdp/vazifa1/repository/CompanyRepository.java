package uz.pdp.vazifa1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.vazifa1.entity.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer> {

    boolean existsByAddressIdAndCorpName(Integer address_id, String corpName);

    boolean existsByAddressId(Integer address_id);

    boolean existsByAddressIdAndIdNot(Integer address_id, Integer id);

}
