package appmanager;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ApplicationManager {

    private final Properties properties;
    private DbHelper db;
    private Generator generate;
    private P2PPublisher p2p;

    public ApplicationManager() {
        properties = new Properties();
    }

    public void init() throws IOException {
        String target = System.getProperty("target", "local");
        properties.load(new FileReader(String.format("src/test/resources/%s.properties", target)));
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    public DbHelper db() {
        if(db == null) {
            db = new DbHelper(this);
        }
        return db;
    }

    public Generator generate() {
        if(generate == null) {
            generate = new Generator(this);
        }
        return generate;
    }

    public P2PPublisher p2p() {
        if(p2p == null) {
            p2p = new P2PPublisher(this);
        }
        return p2p;
    }
}
