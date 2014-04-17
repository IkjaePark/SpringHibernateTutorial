package com.inpcreations.BlogEntry.service;

import java.util.List;
import java.util.Map;

@SuppressWarnings("rawtypes")
public interface ICrudService<T>
{
    public void create( T t );
    public T get( Class<T> t, int id );
    public void update( T t );
    public void delete( T t );
    public List<T> findWithNamedQuery( String queryStr, Map parameters, int resultLimit );
    public void refreshRecord( T t );
}