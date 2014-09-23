package com.inpcreations.BlogEntry.dao;

import java.util.List;
import java.util.Map;

@SuppressWarnings("rawtypes")
public interface ICrudDAO<T>
{
    public void create( T t );
    public T get( Class type, int id );
    public void update( T t );
    public void delete( T t );
    public List<T> findWithNamedQuery( String queryStr, Map parameters, int resultLimit );
    public void refreshRecord( T t );
}