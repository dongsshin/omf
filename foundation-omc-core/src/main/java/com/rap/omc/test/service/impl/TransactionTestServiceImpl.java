package com.rap.omc.test.service.impl;

import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.rap.config.datasource.dao.FoundationDao;
import com.rap.config.datasource.dao.MasterDao;
import com.rap.omc.test.service.TransactionTestService;
@Service("transactionTestService")
public class TransactionTestServiceImpl implements  TransactionTestService{
    
	@Resource(name = "foundationDao")
    private FoundationDao foundationDao;
    
	@Resource(name = "masterDao")
    private MasterDao masterDao;
	
	//@Transactional
	@Override
	public void txnMasterTest() {
		masterTestSub();
	}
	private void masterTestSub() {
		HashMap<String,String> map = new HashMap<String,String>();
		map.put("obid", "obid");
		masterDao.insert("Common1.masterTestInsert", map);
	}
	//@Transactional
	@Override
	public void txnFoundationTest() {
		foundationTestSub();
	}
	private void foundationTestSub() {
		HashMap<String,String> map = new HashMap<String,String>();
		map.put("obid", "obid");
		foundationDao.insert("Common1.foundationTestInsert", map);
		
	}
	//@Transactional
	@Override
	public void txnAllTest() {
		masterTestSub();
		foundationTestSub();
		
	}
	@Override
	public void txnMasterTestError() {
		masterTestSub();
		Integer dd = Integer.parseInt("ddd");
	}
	@Override
	public void txnFoundationTestError() {
		foundationTestSub();
		Integer dd = Integer.parseInt("ddd");
	}
	@Override
	public void txnAllTestError() {
		masterTestSub();
		foundationTestSub();
		Integer dd = Integer.parseInt("ddd");
		
	}
}
