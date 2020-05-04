package net.thumbtack.footballgame.configure;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigUtils {
    private int serverPort;
    private String adminEmail;
    private String adminPassword;
    public ConfigUtils(String propFileName) {
        Properties prop = new Properties();
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName)) {
            prop.load(inputStream);


            serverPort = Integer.parseInt(prop.getProperty("server.port"));
            adminEmail = prop.getProperty("admin.email");
            adminPassword = prop.getProperty("admin.password");

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public int getServerPort() {
        return serverPort;
    }

    public String getAdminEmail() {
        return adminEmail;
    }

    public String getAdminPassword() {
        return adminPassword;
    }
}
