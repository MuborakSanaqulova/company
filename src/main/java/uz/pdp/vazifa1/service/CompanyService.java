package uz.pdp.vazifa1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.vazifa1.common.ApiResponse;
import uz.pdp.vazifa1.entity.Address;
import uz.pdp.vazifa1.entity.Company;
import uz.pdp.vazifa1.payload.CompanyDto;
import uz.pdp.vazifa1.repository.AddressRepository;
import uz.pdp.vazifa1.repository.CompanyRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    AddressRepository addressRepository;

    public List<Company> getCompanies() {
        return companyRepository.findAll();
    }

    public Company getCompany(Integer id) {
        Optional<Company> byId = companyRepository.findById(id);
        if (byId.isPresent())
            return byId.get();
        return null;
    }

    public ApiResponse addCompany(CompanyDto companyDto) {

        Optional<Address> optionalAddress = addressRepository.findById(companyDto.getAddressId());
        if (!optionalAddress.isPresent())
            return new ApiResponse("address not found ", false);

        boolean addressIdAndCorpNameAndDirectorName = companyRepository.existsByAddressIdAndCorpName(
                companyDto.getAddressId(), companyDto.getCorpName());
        if (addressIdAndCorpNameAndDirectorName)
            return new ApiResponse("already exist company",false);

        boolean byAddressId = companyRepository.existsByAddressId(companyDto.getAddressId());
        if (byAddressId) return new ApiResponse("this address is already taken", false);

        Company company = new Company();
        company.setAddress(optionalAddress.get());
        company.setCorpName(companyDto.getCorpName());
        company.setDirectorName(companyDto.getDirectorName());

        companyRepository.save(company);
        return new ApiResponse("company added", true);
    }

    public ApiResponse editCompany(Integer id, CompanyDto companyDto) {
        Optional<Company> optionalCompany = companyRepository.findById(id);
        if (!optionalCompany.isPresent())
            return new ApiResponse("company not found", false);

        Optional<Address> optionalAddress = addressRepository.findById(companyDto.getAddressId());
        if (!optionalAddress.isPresent())
            return new ApiResponse("address not found ", false);

        boolean byAddressId = companyRepository.existsByAddressIdAndIdNot(companyDto.getAddressId(), id);
        if (byAddressId) return new ApiResponse("this address is already taken", false);

        Company company = new Company();
        company.setId(id);
        company.setAddress(optionalAddress.get());
        company.setCorpName(companyDto.getCorpName());
        company.setDirectorName(companyDto.getDirectorName());

        companyRepository.save(company);
        return new ApiResponse("company edited", true);

    }

    public ApiResponse deleteCompany(Integer id) {
        Optional<Company> optionalCompany = companyRepository.findById(id);
        if (!optionalCompany.isPresent())
            return new ApiResponse("company not found", false);

        companyRepository.deleteById(id);
        return new ApiResponse("successfully deleted", true);
    }
}
