package github.cozyplugins.cozyvulengateeventapi.api;

import com.github.smuddgge.squishydatabase.interfaces.Database;
import github.cozyplugins.cozyvulengateeventapi.CozyVulengateEventAPI;
import org.bukkit.Bukkit;
import org.jetbrains.annotations.Nullable;

public final class VulengateEventsAPI {

    /**
     * Used to get the instance of the database.
     *
     * @return The instance of the database.
     */
    public static @Nullable Database getDatabase() {
        CozyVulengateEventAPI api = (CozyVulengateEventAPI) Bukkit.getPluginManager().getPlugin("CozyVulengateEventAPI");
        return api.getDatabase();
    }
}
