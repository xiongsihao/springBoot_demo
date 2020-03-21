package com.xsh.dao;

import com.xsh.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author : xsh
 * @create : 2019-10-11 - 16:40
 * @describe:
 */
public interface AccountDao extends JpaRepository<Account,Integer> {
    List<Account> findAll();
}
