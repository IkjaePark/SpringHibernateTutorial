package com.inpcreations.BlogEntry.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.inpcreations.BlogEntry.dao.CrudDAO;

/**
 * @author      Ikjae Park <park.ikjae@gmail.com>
 * @version     1.0
 * @since       2014-03-03
 */
@Transactional(readOnly = true)
public class CrudService<T> implements ICrudService<T>, Serializable
{
	private static final long serialVersionUID = 680780452535106338L;

	/**
	 * crudDAO object will be loaded from the configuration (All of the method will utilze the CrudDAO method)
	 */  
	private CrudDAO<T> crudDAO;
	
	
	@Transactional(readOnly = false)
	public void create( T t )
	{
		crudDAO.create( t );
	}

	
	public T get( Class<T> t, int id )
	{
		return (T) crudDAO.get( t, id );
	}

	
	@Transactional(readOnly = false)
	public void update(T t )
	{
		crudDAO.update( t );
	}

	
	@Transactional(readOnly = false)
	public void delete(T t )
	{
		crudDAO.delete( t );
	}
	
	
	@SuppressWarnings("rawtypes")
	public List<T> findWithNamedQuery(String queryStr, Map parameters, int resultLimit)
	{
		return crudDAO.findWithNamedQuery( queryStr, parameters, resultLimit );
	}
	
	
	public void refreshRecord( T t )
	{
		crudDAO.refreshRecord( t );
	}
	
	
	public CrudDAO<T> getCrudDAO()
	{
		return crudDAO;
	}	
	public void setCrudDAO( CrudDAO<T> crudDAO )
	{
		this.crudDAO = crudDAO;
	}
}