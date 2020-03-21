package com.xsh.Controller;

import com.xsh.dao.IAccountDao;
import com.xsh.domain.Account;
import com.xsh.domain.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : xsh
 * @create : 2019-08-30 - 13:27
 * @describe:
 */
@Controller
@RequestMapping("crud")
public class AccountController {

    @Autowired
    private IAccountDao accountDao;

    @ResponseBody
    @RequestMapping(value="account",method = RequestMethod.GET)
    public Msg findAll(){
        List<Account> accounts = accountDao.findAll();
        return Msg.success().add("查询全部账号信息",accounts);
    }

    @ResponseBody
    @RequestMapping(value="account/{id}",method=RequestMethod.GET)
    public Msg findById(@PathVariable("id") int accountId){
        int a=accountDao.findId(accountId);
        if(a==1){
            Account account=accountDao.findById(accountId);
            return Msg.success().add("查询单个账号信息",account);
        }else if(a==0){
            return Msg.fail().add("账号不存在",null);
        }
        return null;
    }
    @RequestMapping(value="account",method = RequestMethod.POST)
    public ResponseEntity<Object> insert(Account account){
        Account select_account=accountDao.findByName(account.getName());//根据传入的名字参数查询账户名字是否存在
        if(select_account!=null){
            return new ResponseEntity<>(Msg.fail().add("添加账号失败,账号名称已存在",select_account),HttpStatus.CREATED);
        }
        Account insert=new Account();
        insert.setName(account.getName());
        insert.setMoney(account.getMoney());
        accountDao.insert(insert);
        Account insert_account=accountDao.findByName(account.getName());//根据传入的名字参数查询账户信息并返回到前端
        return new ResponseEntity<>(Msg.success().add("添加账号成功",insert_account),HttpStatus.CREATED);
    }
    @RequestMapping(value="account/{id}",method = RequestMethod.DELETE)
    /*ResponseEntity通过编程方式指明响应状态，所以根据不同场景返回不同状态*/
    public ResponseEntity<Object> deleteById(@PathVariable("id") int accountId){
        int a = accountDao.findId(accountId);
        if(a==1){
            int result= accountDao.delete(accountId);
            if (result==1){
                return new ResponseEntity<>(Msg.success().add("删除成功",accountId),HttpStatus.OK);
            }else{
                return new ResponseEntity<>(Msg.success().add("删除失败",accountId),HttpStatus.OK);
            }
        }else if(a==0){
            return new ResponseEntity<>(Msg.success().add("删除失败,账号id不存在",accountId), HttpStatus.OK);
        }
        return null;
    }

    @RequestMapping(value = "/account/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Object> update(@PathVariable("id") int accountId,Account account){
        Account update=new Account();
        update.setName(account.getName());
        update.setMoney(account.getMoney());
        update.setId(accountId);
        int updateResult=accountDao.update(update);
        Account updateAccount= accountDao.findById(accountId);
        if(updateResult==1){
            return new ResponseEntity<>(Msg.success().add("更新成功",updateAccount), HttpStatus.OK);
        }else if(updateResult==0){
            return new ResponseEntity<>(Msg.fail().add("更新失败",null), HttpStatus.OK);
        }
        return null;
    }

}
