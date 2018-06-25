package com.psdkp.api.address.repository;

import com.psdkp.api.address.domain.District;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DistrictDao extends PagingAndSortingRepository<District, Integer> {

    @Query(value = "select * from district p where p.name like %?1% or p.code like %?1%", nativeQuery = true)
    Page<District> findAllByName(String name, Pageable pageable);

    District findByCode(String code);
    District findByName(String name);

    @Query(value = "select * from district p where p.id=?1", nativeQuery = true)
    District findId(Integer id);
}
