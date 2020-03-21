package com.xsh.dao;

import com.xsh.domain.Account;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author : xsh
 * @create : 2019-08-30 - 13:26
 * @describe:
 */
@Mapper
@Repository
public interface IAccountDao {
    List<Account> findAll();
    Account findById(int id);
    Account findByName(String name);
    int findId(int id);//查询账号是否存在
    int insert(Account account);
    int delete(int id);
    int update(Account account);
}
