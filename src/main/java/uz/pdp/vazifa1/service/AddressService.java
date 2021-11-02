package uz.pdp.vazifa1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.vazifa1.common.ApiResponse;
import uz.pdp.vazifa1.entity.Address;
import uz.pdp.vazifa1.payload.AddressDto;
import uz.pdp.vazifa1.repository.AddressRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {

    @Autowired
    AddressRepository addressRepository;

    public List<Address> getAddresses() {

        return addressRepository.findAll();

    }

    public Address getAddress(Integer id) {
        Optional<Address> byId = addressRepository.findById(id);
        if (byId.isPresent())
            return byId.get();
        return null;
    }

    public ApiResponse addAddress(AddressDto addressDto) {

        boolean numberAndStreet = addressRepository.existsByHomeNumberAndStreet(addressDto.getHomeNumber(), addressDto.getStreet());
        if (numberAndStreet)
            return new ApiResponse("already exist address", false);

        Address address = new Address();
        address.setStreet(addressDto.getStreet());
        address.setHomeNumber(addressDto.getHomeNumber());

        addressRepository.save(address);
        return new ApiResponse("successfully added", true);

    }

    public ApiResponse editAddress(Integer id, AddressDto addressDto) {
        Optional<Address> optionalAddress = addressRepository.findById(id);
        if (!optionalAddress.isPresent())
            return new ApiResponse("address not found", false);

        boolean numberAndStreet = addressRepository.existsByHomeNumberAndStreetAndIdNot(addressDto.getHomeNumber(), addressDto.getStreet(), id);
        if (numberAndStreet)
            return new ApiResponse("this address already exist", false);

        Address address = new Address();
        address.setId(id);
        address.setStreet(addressDto.getStreet());
        address.setHomeNumber(addressDto.getHomeNumber());

        addressRepository.save(address);
        return new ApiResponse("successfully edited", true);

    }

    public ApiResponse deleteAddress(Integer id) {
        Optional<Address> optionalAddress = addressRepository.findById(id);
        if (!optionalAddress.isPresent())
            return new ApiResponse("address not found", false);

        addressRepository.deleteById(id);
        return new ApiResponse("address deleted", true);
    }
}
