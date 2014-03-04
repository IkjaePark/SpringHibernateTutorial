package com.inpcreations.BlogEntry.dao;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.inpcreations.BlogEntry.model.Contact;
import com.inpcreations.BlogEntry.service.CrudService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/context.xml")
public class CrudServiceTest
{
	@Autowired
	private CrudService<Contact> crudService;
	
	private Contact newContact;
	
	@Before
    public final void setUp()
	{
		newContact = new Contact();
		newContact.setName( "test" );
		newContact.setPhone( "111-111-1111" );
		
		crudService.create( newContact );
	}
	
	
	@Test
	public void query()
	{
		assertEquals( 1, crudService.findWithNamedQuery( "FROM Contact", null, 0 ).size());
	}
	
	
	@After
    public final void destory()
	{
		crudService.refreshRecord( newContact );		
		crudService.delete( newContact );
	}
}
