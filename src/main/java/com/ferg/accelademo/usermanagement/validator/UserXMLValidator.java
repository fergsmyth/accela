package com.ferg.accelademo.usermanagement.validator;

import com.ferg.accelademo.usermanagement.dto.UserXMLDTO;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;

@Component
public class UserXMLValidator {

    private static final String USER_XSD = "src/main/resources/user.xsd";

    @Autowired
    private UserArgumentValidator userArgumentValidator;

    public boolean validateUserXML(String[] args){
        boolean isValid = false;
        if(userArgumentValidator.validateExpectedArgumentNumber(args, 2)){
            String filePath = args[1].trim();
            if(validateFilePath(filePath)) {
                try {
                    JAXBContext jaxbContext = JAXBContext.newInstance(UserXMLDTO.class);
                    SchemaFactory sf = SchemaFactory.newInstance( XMLConstants.W3C_XML_SCHEMA_NS_URI );
                    Schema schema = sf.newSchema(new File(USER_XSD));
                    Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
                    jaxbUnmarshaller.setSchema(schema);
                    UserXMLDTO userXMLDTO = (UserXMLDTO) jaxbUnmarshaller.unmarshal(new File(filePath));
                    if(userArgumentValidator.validateNoUserExists(userXMLDTO.getUserId())){
                        isValid = true;
                    }
                } catch (JAXBException | SAXException e) {
                    System.out.println(e);
                }
            }
        }
        return isValid;
    }

    private boolean validateFilePath(String filePath){
        boolean isValid = true;
        File xmlFile = new File(filePath);
        if(!xmlFile.exists()){
            System.out.println(String.format("No file exists at location %s", filePath));
            isValid = false;
        } else if(!FilenameUtils.getExtension(filePath).equalsIgnoreCase("xml")){
            System.out.println("File does not match the expected file type xml");
            isValid = false;
        }
        return isValid;
    }

}
