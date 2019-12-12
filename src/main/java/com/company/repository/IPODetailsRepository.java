package com.company.repository;

import com.company.domain.IPODetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IPODetailsRepository extends JpaRepository<IPODetails,Long> {
    public IPODetails findById(long id);

    @Query(value = "select * from ipodetails i order by i.opendatetime desc" , nativeQuery = true)
    public List<IPODetails > getAllIpoDetails();

    @Query(value = "select * from ipodetails i where i.companyname like %?1% order by i.opendatetime desc" , nativeQuery = true)
    public List<IPODetails > getAllIpoDetailsByCompanyName(String companyName);
}
