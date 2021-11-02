package uz.pdp.vazifa1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.vazifa1.entity.Worker;

public interface WorkerRepository extends JpaRepository<Worker, Integer> {

    boolean existsByPhoneNumber(String phoneNumber);

    boolean existsByPhoneNumberAndIdNot(String phoneNumber, Integer id);

}
