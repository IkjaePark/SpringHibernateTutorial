package com.inpcreations.BlogEntry.controller;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.inpcreations.BlogEntry.model.Contact;
import com.inpcreations.BlogEntry.service.CrudService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations ={"classpath:/context.xml"})
public class MainControllerTest
{
	@Autowired
	private CrudService<Contact> crudService;
	
	private Contact newContact;
	
	@InjectMocks
    private MainController controller;
	
	private MockMvc mockMvc;
	
	@Before
    public void setUp()
	{
		MockitoAnnotations.initMocks( this );
		
		newContact = new Contact();
		newContact.setName( "test" );
		newContact.setPhone( "111-111-1111" );
		
		crudService.create( newContact );
		
		mockMvc = MockMvcBuilders.standaloneSetup( controller ).build();
    }
	
	@Test
    public void retreiveContactTest() throws Exception
    {
		this.mockMvc.perform( get( "/contacts" ).param( "id", String.valueOf( newContact.getId() )))
		   .andExpect( status().isOk() );
    }

	
	@After
    public final void destory()
	{
		crudService.refreshRecord( newContact );		
		crudService.delete( newContact );
	}
}
