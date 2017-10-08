package by.itacademy;

import by.itacademy.entities.Reader;

import java.util.List;

/**
 * Project KR. Created by masiuk-l on 06.08.2017.
 */
public interface ReaderService extends Service<Reader> {
    /**
     * @param surname surname
     * @return List of readers matching the input
     */
    List<Reader> getBySurname(String surname);

    /**
     * @param login login
     * @return Reader matching the input
     */
    Reader getByLogin(String login);

    /**
     * @param status status
     * @return List of readers matching the input
     */
    List<Reader> getByStatus(String status);

    /**
     * @param reader reader
     * @return Value object of the reader
     */
//    ReaderVO getReaderVO(Reader reader);

    /**
     * @param oldReader initial reader
     * @param newReader updated info
     */
    void update(Reader oldReader, Reader newReader);
}
