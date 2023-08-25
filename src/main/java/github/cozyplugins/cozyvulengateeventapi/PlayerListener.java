package github.cozyplugins.cozyvulengateeventapi;

import com.github.cozyplugins.cozylibrary.user.PlayerUser;
import com.github.smuddgge.squishydatabase.interfaces.Database;
import github.cozyplugins.cozyvulengateeventapi.general.PlayerTable;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

/**
 * Contains methods that will listen to player
 * events.
 */
public class PlayerListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        PlayerUser user = new PlayerUser(event.getPlayer());

        // Attempt to get the database.
        Database database = CozyVulengateEventAPI.getDatabase();
        if (database == null) return;

        // Update the player's record.
        database.getTable(PlayerTable.class).updatePlayer(user);
    }
}
