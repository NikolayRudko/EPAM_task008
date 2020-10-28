package com.epam.task.eighth.parser.SAX;

import com.epam.task.eighth.model.AbstractTouristVoucher;
import com.epam.task.eighth.parser.Parser;
import com.epam.task.eighth.parser.ParserException;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;
import java.util.List;

public class SaxParser implements Parser {


    @Override
    public List<AbstractTouristVoucher> parse(String file) throws ParserException {
        List<AbstractTouristVoucher> vouchers;
        VoucherHandler handler = new VoucherHandler();

        try {
            XMLReader reader = XMLReaderFactory.createXMLReader();
            reader.setContentHandler(handler);
            reader.parse(file);
        } catch (SAXException | IOException e) {
            throw new ParserException(e.getMessage(), e);
        }
        vouchers = handler.getVouchers();
        return vouchers;
    }

}
