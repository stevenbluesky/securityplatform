package cn.com.isurpass.house.dao;

import cn.com.isurpass.house.po.SupplierCodeLogPO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierCodeLogDAO extends CrudRepository<SupplierCodeLogPO,String> {
    @Query(value = "select * from suppliercodelog where suppliercode=:code for UPDATE ",nativeQuery = true)
    SupplierCodeLogPO findSupplierCode(@Param("code") String code);
}
