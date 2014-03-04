package com.inpcreations.BlogEntry.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;
/**
 * @author      Ikjae Park <park.ikjae@gmail.com>
 * @version     1.0
 * @since       2014-03-03
 */
@Component( "crudDAO" )
public class CrudDAO<T> implements ICrudDAO<T>, Serializable
{
	private static final long serialVersionUID = -8699164546094431833L;
	
	/**
	 * SessionFactory will be loaded from the configuration
	 */
	@Resource(name="SessionFactory")
	private SessionFactory sessionFactory;

	/**
	 * @return the Hibnerate sessionFactory
	 */
	public SessionFactory getSessionFactory()
	{
		return sessionFactory;
	}
	/**
	 * @param the hibernate sessionFactory
	 */
	public void setSessionFactory( SessionFactory sessionFactory )
	{
        this.sessionFactory = sessionFactory;
    }


	/**
	 * Creates a database record using generic object type
	 *
	 * @param t the transaction generic object that will get created in the database
	 */
	public void create( T t )
	{
		sessionFactory.getCurrentSession().save( t );
	}
	 
	
	/**
	 * Fetches a  database record using generic object type
	 *
	 * @param type the transaction generic object that type can be referenced
	 * @param id   id of the record
	 * 
	 * @return a single query result 
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public T get( Class type, int id  )
	{
		return (T) sessionFactory.getCurrentSession().get( type, id );
	}


	/**
	 * Deletes a single database recording using generic object type
	 *
	 * @param type the transaction generic object that type can be deleted
	 *
	 */
    public void delete( T t )
    {
    	sessionFactory.getCurrentSession().delete( t );
    }

    
    /**
	 * Updates a database record using generic object type
	 *
	 * @param t the transaction generic object that will get updated in the database
	 */
    public void update( T t )
    {
    	sessionFactory.getCurrentSession().update( t );
    }
    
	
    /**
	 * Fetches multiple database records using Hibernate Query with query parameter Map
	 *
	 * @param queryStr    HSQ string that will be excuted
	 * @param parameters  query parameter map
	 * @param resultLimit size limit for the query
	 * 
	 * @return a generic list that fetched by HQL
	 */
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public List<T> findWithNamedQuery( String queryStr, Map parameters, int resultLimit )
	{
		Query myQuery = sessionFactory.getCurrentSession().createQuery( queryStr );
		
		if (parameters != null)
		{
			Set<Entry> rawParameters = parameters.entrySet();
			
			for (Entry entry : rawParameters)
			{
				myQuery.setParameter( (String) entry.getKey(), entry.getValue() );
	        }
			
			if (resultLimit > 0)
			{
				myQuery.setMaxResults( resultLimit );
			}
		}
		
		return (List<T>) myQuery.list();
	}
    
  
    /**
	 * Re-fetches a single database recording using generic object type
	 *
	 * @param type the transaction generic object that needs to be re-fetched
	 *
	 */
    public void refreshRecord( T t )
    {
    	sessionFactory.getCurrentSession().refresh( t );
    }
}