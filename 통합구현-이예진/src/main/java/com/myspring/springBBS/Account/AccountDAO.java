package com.myspring.springBBS.Account;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.myspring.springBBS.Login.LoginDTO;

@Repository
@Component
public class AccountDAO {
	@Autowired
	SqlSessionTemplate temp;
	
	public String dbCheck(String id) {
		int ck=(Integer)temp.selectOne("acct.idCheck",id);
		return ck>0 ? "t":"f"; // t �ߺ� ���̵� / f ��� ���� 
	}
	
	
	public void dbInsert(AccountDTO dto) {
		temp.insert("acct.insert", dto);
		System.out.println("�ٿ�AccountDAO.java  acct���̺� �űԵ�� success");
		temp.insert("login.insert", dto);
		System.out.println("�ٿ�AccountDAO.java login���̺� �űԵ�� success");
	}
	
}//class EDN
