package db.orm;

import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;
import java.nio.file.Path;

public interface Properties {
    Properties createFromXML(Path path) throws XPathExpressionException, IOException;

    Properties update();
    String getProperty(String name);
    void setProperty(String name, String value);
}
