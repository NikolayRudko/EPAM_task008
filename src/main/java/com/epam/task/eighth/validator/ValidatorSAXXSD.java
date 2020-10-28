package com.epam.task.eighth.validator;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;


public class ValidatorSAXXSD {
    private final static Logger LOGGER = Logger.getLogger(ValidatorSAXXSD.class);
    private final String schemaLocation;

    public ValidatorSAXXSD(String schemaLocation) {
        this.schemaLocation = schemaLocation;
    }

    public boolean valid(String fileName){
        String language = XMLConstants.W3C_XML_SCHEMA_NS_URI;
        SchemaFactory factory = SchemaFactory.newInstance(language);
        File schemaFile = new File(schemaLocation);

        boolean isValid;

        try {
            Schema schema = factory.newSchema(schemaFile);
            Validator validator = schema.newValidator();
            Source source = new StreamSource(fileName);
            validator.validate(source);
            isValid = true;
        } catch (SAXException | IOException e) {
            LOGGER.error(e.getMessage(),e);
            isValid = false;
        }

        return isValid;
    }
}
