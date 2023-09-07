package github.cozyplugins.cozyvulengateeventapi.utility;

import com.github.cozyplugins.cozylibrary.user.PlayerUser;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Represents player utility.
 */
public final class PlayerUtility {

    /**
     * Used to get the player's color.
     *
     * @param user The instance of the user.
     * @return The color.
     */
    public static @Nullable String getPlayerColour(@NotNull PlayerUser user) {
        if (user.hasPermission("team.red")) return "red";
        if (user.hasPermission("team.orange")) return "orange";
        if (user.hasPermission("team.yellow")) return "yellow";
        if (user.hasPermission("team.green")) return "green";
        if (user.hasPermission("team.blue")) return "blue";
        if (user.hasPermission("team.purple")) return "purple";
        return null;
    }
}
