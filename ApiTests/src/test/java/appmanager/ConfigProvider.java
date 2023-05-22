package appmanager;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public interface ConfigProvider {
	Config config = readConfig();

	static Config readConfig() {
		return ConfigFactory.systemProperties().hasPath("testProfile")
				? ConfigFactory.load(ConfigFactory.systemProperties().getString("testProfile"))
				: ConfigFactory.load("local.conf");
	}

	String URL = readConfig().getString("url");
	String RABBIT_HOST = readConfig().getString("RabbitMQ.host");
}
