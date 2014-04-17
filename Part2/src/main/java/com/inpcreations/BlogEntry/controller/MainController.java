package com.inpcreations.BlogEntry.controller;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.inpcreations.BlogEntry.model.Contact;
import com.inpcreations.BlogEntry.service.CrudService;

@Controller
public class MainController implements Serializable
{
	private static final long serialVersionUID = -1531934923275408973L;
	
	@Autowired
	private CrudService<Contact> crudService;
	
	/**
	 * Fetches a single contact record
	 *
	 * @param  id ID of the reocrd 
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "contact/{contactID}", 
			        method = RequestMethod.GET,
			        produces = {MediaType.APPLICATION_JSON_VALUE})
	public @ResponseBody Contact retreiveContact( @PathVariable("contactID") int id )
	{
		Contact myContact = null;
		
		Map filter = new HashMap();
		filter.put( "id", id );
		
		List<Contact> result = crudService.findWithNamedQuery( "FROM Contact WHERE id = :id", filter, 1 );
		
		if (result != null)
		{
			myContact = result.get( 0 );
		}
		
		return myContact;
	}
}
