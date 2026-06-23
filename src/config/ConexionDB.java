/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package config;

/**
 *
 * @author Kevin
 */
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class ConexionDB {

    private static String url;
    private static String user;
    private static String password;
    private static String driver;

    static {
        try {
            InputStream input = ConexionDB.class.getClassLoader().getResourceAsStream("meta/persistence.xml");
            if (input == null) {
                throw new RuntimeException("No se encontro el archivo persistence.xml");
            }
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(input);
            NodeList list = doc.getElementsByTagName("property");
            for (int i = 0; i < list.getLength(); i++) {
                Element element = (Element) list.item(i);
                String name = element.getAttribute("name");
                String value = element.getAttribute("value");
                switch (name) {
                    case "javax.persistence.jdbc.url":
                        url = value;
                        break;
                    case "javax.persistence.jdbc.user":
                        user = value;
                        break;
                    case "javax.persistence.jdbc.password":
                        password = value;
                        break;
                    case "javax.persistence.jdbc.driver":
                        driver = value;
                        break;
                }
            }
            Class.forName(driver);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al inicializar la configuracion de la base de datos");
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
}
