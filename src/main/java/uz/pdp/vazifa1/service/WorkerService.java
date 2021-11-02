package uz.pdp.vazifa1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.vazifa1.common.ApiResponse;
import uz.pdp.vazifa1.entity.Address;
import uz.pdp.vazifa1.entity.Company;
import uz.pdp.vazifa1.entity.Department;
import uz.pdp.vazifa1.entity.Worker;
import uz.pdp.vazifa1.payload.DepartmentDto;
import uz.pdp.vazifa1.payload.WorkerDto;
import uz.pdp.vazifa1.repository.AddressRepository;
import uz.pdp.vazifa1.repository.CompanyRepository;
import uz.pdp.vazifa1.repository.DepartmentRepository;
import uz.pdp.vazifa1.repository.WorkerRepository;

import java.util.List;
import java.util.Optional;

@Service
public class WorkerService {
    @Autowired
    WorkerRepository workerRepository;

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    DepartmentRepository departmentRepository;

    public List<Worker> getWorkers() {
        return workerRepository.findAll();
    }

    public Worker getWorker(Integer id) {
        Optional<Worker> byId = workerRepository.findById(id);
        if (byId.isPresent())
            return byId.get();
        return null;
    }

    public ApiResponse addWorker(WorkerDto workerDto) {

        Optional<Department> optionalDepartment = departmentRepository.findById(workerDto.getDepartmentId());
        if (!optionalDepartment.isPresent())
            return new ApiResponse("department not found ", false);

        Optional<Address> optionalAddress = addressRepository.findById(workerDto.getAddressId());
        if (!optionalAddress.isPresent())
            return new ApiResponse("address not found ", false);

        boolean byPhoneNumber = workerRepository.existsByPhoneNumber(workerDto.getPhoneNumber());
        if (byPhoneNumber)
            return new ApiResponse("already exist worker", false);

        Worker worker = new Worker();
        worker.setAddress(optionalAddress.get());
        worker.setDepartment(optionalDepartment.get());
        worker.setPhoneNumber(workerDto.getPhoneNumber());
        worker.setName(workerDto.getName());

        workerRepository.save(worker);
        return new ApiResponse("worker added", true);
    }

    public ApiResponse editWorker(Integer id, WorkerDto workerDto) {
        Optional<Worker> optionalWorker = workerRepository.findById(id);
        if (!optionalWorker.isPresent())
            return new ApiResponse("worker not found", false);

        Optional<Department> optionalDepartment = departmentRepository.findById(workerDto.getDepartmentId());
        if (!optionalDepartment.isPresent())
            return new ApiResponse("department not found ", false);

        Optional<Address> optionalAddress = addressRepository.findById(workerDto.getAddressId());
        if (!optionalAddress.isPresent())
            return new ApiResponse("address not found ", false);


        boolean idNot = workerRepository.existsByPhoneNumberAndIdNot(workerDto.getPhoneNumber(), id);
        if (idNot)
            return new ApiResponse("this worker already exist", false);

        Worker worker = new Worker();
        worker.setId(id);
        worker.setAddress(optionalAddress.get());
        worker.setDepartment(optionalDepartment.get());
        worker.setPhoneNumber(workerDto.getPhoneNumber());
        worker.setName(workerDto.getName());

        workerRepository.save(worker);
        return new ApiResponse("worker edited", true);

    }

    public ApiResponse deleteWorker(Integer id) {
        Optional<Worker> optionalWorker = workerRepository.findById(id);
        if (!optionalWorker.isPresent())
            return new ApiResponse("worker not found", false);

        workerRepository.deleteById(id);
        return new ApiResponse("successfully deleted", true);
    }


}
