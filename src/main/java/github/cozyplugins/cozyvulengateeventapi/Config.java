package github.cozyplugins.cozyvulengateeventapi;

import com.github.cozyplugins.cozylibrary.CozyPlugin;
import com.github.smuddgge.squishyconfiguration.implementation.yaml.YamlConfiguration;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

/**
 * Represents this plugin's configuration file.
 * Used to get the database connection infomation.
 */
public final class Config {

    private static YamlConfiguration configuration;

    /**
     * Used to create the default configuration file.
     * If it was created this method will return true.
     * If the file already exists it will not create the file.
     *
     * @return True if the file was created.
     */
    private static void createDefault() {
        File file = new File(CozyPlugin.getPlugin().getDataFolder(), "config.yml");
        if (file.exists()) return;

        // Attempt to copy the configuration file.
        try (InputStream input = CozyPlugin.class.getResourceAsStream("/config.yml")) {

            if (input != null) Files.copy(input, file.toPath());

        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    /**
     * Used to reload this configuration file.
     */
    public static void reload() {
        File file = new File(CozyPlugin.getPlugin().getDataFolder(), "config.yml");

        // Attempt to create the default file.
        Config.createDefault();

        // Create configuration instance.
        Config.configuration = new YamlConfiguration(file);
        Config.configuration.load();
    }

    /**
     * Used to get the instance of the connection string.
     *
     * @return The mongo connection string.
     */
    public static @NotNull String getConnectionString() {
        return Config.configuration.getString("connection_string", "null");
    }

    /**
     * Used to get the database's name.
     *
     * @return The database's name.
     */
    public static @NotNull String getDatabaseName() {
        return Config.configuration.getString("database_same", "null");
    }
}
