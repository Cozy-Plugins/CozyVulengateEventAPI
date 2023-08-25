package github.cozyplugins.cozyvulengateeventapi;

import com.github.cozyplugins.cozylibrary.CozyPlugin;
import com.github.smuddgge.squishydatabase.DatabaseCredentials;
import com.github.smuddgge.squishydatabase.DatabaseFactory;
import com.github.smuddgge.squishydatabase.interfaces.Database;
import github.cozyplugins.cozyvulengateeventapi.api.general.PlayerTable;
import org.jetbrains.annotations.Nullable;

/**
 * Represents the cozy vulengate event api.
 */
public final class CozyVulengateEventAPI extends CozyPlugin {

    /**
     * The instance of the database.
     */
    private static Database database;

    @Override
    public boolean enableCommandDirectory() {
        return false;
    }

    @Override
    public void onCozyEnable() {

        // Load configuration.
        Config.reload();

        // Create database.
        database = DatabaseFactory.MONGO.create(DatabaseCredentials.MONGO(
                Config.getConnectionString(),
                Config.getDatabaseName()
        ));

        // Add records.
        database.createTable(new PlayerTable());

        // Listen to events.
        this.getServer().getPluginManager().registerEvents(new PlayerListener(), this);
    }

    /**
     * Used to get the instance of the database.
     *
     * @return The instance of the database.
     */
    public @Nullable Database getDatabase() {
        return CozyVulengateEventAPI.database;
    }
}
