package cn.com.isurpass.house.dao;

import cn.com.isurpass.house.po.CodeLog;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CodeLogDAO extends CrudRepository<CodeLog,Integer> {
    @Query(value = "select * from codelog where id=:code for UPDATE ",nativeQuery = true)
    CodeLog findCodeLog(@Param("code") String code);
}
