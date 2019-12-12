package com.company.service;

import com.company.domain.IPODetails;
import com.company.model.IPODetailsDTO;
import com.company.repository.IPODetailsRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class IPODetailsService {

    @Autowired
    private IPODetailsRepository ipoDetailsRepository;


    public void editIPODetails(IPODetailsDTO ipoDetailsDTO) {
        IPODetails ipoDetails = new IPODetails();
        BeanUtils.copyProperties(ipoDetailsDTO, ipoDetails);
        ipoDetailsRepository.save(ipoDetails);
    }

    public void saveIPODetails(IPODetailsDTO ipoDetailsDTO) {
        this.editIPODetails(ipoDetailsDTO);
    }

    public void deleteIPODetails(long id) {
        ipoDetailsRepository.deleteById(id);
    }


    public List<IPODetailsDTO> findAll() {
        List<IPODetails> list = ipoDetailsRepository.getAllIpoDetails();
        List<IPODetailsDTO> dtoList = new ArrayList<IPODetailsDTO>();
        for (IPODetails ipoDetails: list) {
            IPODetailsDTO ipoDetailsDTO = new IPODetailsDTO();

            BeanUtils.copyProperties(ipoDetails, ipoDetailsDTO);
            dtoList.add(ipoDetailsDTO);
        }
        return dtoList;
    }

    public List<IPODetailsDTO> getAllIpoDetailsByCompanyName(String companyName) {
        List<IPODetails> list = ipoDetailsRepository.getAllIpoDetailsByCompanyName(companyName);
        List<IPODetailsDTO> dtoList = new ArrayList<IPODetailsDTO>();
        for (IPODetails ipoDetails: list) {
            IPODetailsDTO ipoDetailsDTO = new IPODetailsDTO();

            BeanUtils.copyProperties(ipoDetails, ipoDetailsDTO);
            dtoList.add(ipoDetailsDTO);
        }
        return dtoList;
    }

}
