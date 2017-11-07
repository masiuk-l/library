package by.itacademy.service;

import by.itacademy.entities.Reader;

/**
 * Project KR. Created by masiuk-l on 06.08.2017.
 */
public interface ReaderService extends IService<Reader> {

    /**
     * @param login login
     * @return Reader matching the input
     */
    Reader getByLogin(String login);

    /**
     * @param oldReader initial reader
     * @param newReader updated info
     */
    void update(Reader oldReader, Reader newReader);
}
