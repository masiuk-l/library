package by.itacademy.VO.transfer;

import by.itacademy.VO.FormVO;
import by.itacademy.VO.ReaderVO;
import by.itacademy.entities.Reader;

import java.util.List;

/**
 * Project KR. Created by masiuk-l on 17.08.2017.
 *
 * Static methods to transform Reader to ReaderVO and vice versa
 */
public class ReaderTransfer {
    public static ReaderVO toValueObject(Reader reader, List<FormVO> formVOS) {
        ReaderVO readerVO = new ReaderVO();
        readerVO.setReader(reader);
        readerVO.setFormVOS(formVOS);
        return readerVO;
    }

    public static Reader toEntity(ReaderVO readerVO) {
        Reader reader = readerVO.getReader();
        return reader;
    }
}
