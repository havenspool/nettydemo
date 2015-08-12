package com.havens.nettydemo.db;

import com.havens.nettydemo.server.Server;
import com.havens.nettydemo.utils.FileHelper;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by havens on 15-8-12.
 */
public class DBManager {
    private static final String DB_CONF_FILE = "db.xml";
    private static final String DBOBJECT_CONF_FILE = "DBObject.xml";

    public static final DBConfig DB_CONFIG;

    static {
        DB_CONFIG = readConfig();
    }

    // init manager
    // datasource manger
    // DBObject manager
    // cache manager
    private DBManager() {
    }



    /**
     * read rabbit.xml  数据库连接配置文件和缓存配置文件
     * <p/>
     * read DBObject.xml  数据库表与对象相关连的配置文件
     *
     * @return RabbitConfig
     */
    private static DBConfig readConfig() {
        DBConfig dbConfig = new DBConfig();
        Map<String, DataSourceConf> dataSources = new HashMap<String, DataSourceConf>();
        try {
            Document dd = parseXML(DB_CONF_FILE,false);
            NodeList list = dd.getElementsByTagName("datasource");
            String defaultName = null;
            for (int i = 0; i < list.getLength(); i++) {
                Element tE = (Element) list.item(i);
                String name = tE.getAttribute("name");
                String def = tE.getAttribute("default");
                DataSourceConf df = new DataSourceConf();
                df.name = name;
                if (def != null && "true".equals(def)) {
                    df._default = true;
                    defaultName = name;
                }
                NodeList subList = tE.getElementsByTagName("url");
                if (subList.getLength() > 0) {
                    df.url = subList.item(0).getTextContent();
                }

                subList = tE.getElementsByTagName("user");
                if (subList.getLength() > 0) {
                    df.user = subList.item(0).getTextContent();
                }

                subList = tE.getElementsByTagName("password");
                if (subList.getLength() > 0) {
                    df.password = subList.item(0).getTextContent();
                }

                subList = tE.getElementsByTagName("driver");
                if (subList.getLength() > 0) {
                    df.driver = subList.item(0).getTextContent();
                }

                df.tableToClass = new HashMap<String, String>();
                df.tableExcludes = new HashMap<String, String>();
                df.classToTable = new HashMap<String, String>();
                df.tableToKeyField = new HashMap<String, String>();
                dataSources.put(name, df);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        dbConfig.dataSources=dataSources;
        return dbConfig;
    }

    private static Document parseXML(String filename,
                                     boolean validating) throws Exception {        // Create a builder factory
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setValidating(validating);
        // Prevent expansion of entity references
        factory.setExpandEntityReferences(false);
        // Create the builder and parse the file
        InputStream in = getInputStream(filename);
        return factory.newDocumentBuilder().parse(in);
    }

    private static InputStream getInputStream(String filename) {
        ClassLoader cL = Thread.currentThread().getContextClassLoader();
        if (cL == null) {
            cL = DBManager.class.getClassLoader();
        }
        return cL.getResourceAsStream(filename);
    }

    public static void main(String[] args){
        readConfig();
        System.out.println(readConfig());
    }

}
