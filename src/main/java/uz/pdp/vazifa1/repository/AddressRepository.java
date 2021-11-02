package uz.pdp.vazifa1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.vazifa1.entity.Address;

import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {

    boolean existsByHomeNumberAndStreet(String homeNumber, String street);

    boolean existsByHomeNumberAndStreetAndIdNot(String homeNumber, String street, Integer id);

}
