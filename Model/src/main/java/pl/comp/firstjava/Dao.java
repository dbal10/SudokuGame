package pl.comp.firstjava;

import pl.comp.firstjava.exception.DaoException;

public interface Dao<T> extends AutoCloseable {
    T read() throws DaoException;

    void write(T obj) throws DaoException;

    @Override
    void close() throws Exception;
}
