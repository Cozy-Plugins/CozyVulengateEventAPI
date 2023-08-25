package github.cozyplugins.cozyvulengateeventapi.api.general;

import com.github.cozyplugins.cozylibrary.user.PlayerUser;
import com.github.smuddgge.squishydatabase.Query;
import com.github.smuddgge.squishydatabase.interfaces.TableAdapter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;
import java.util.UUID;

/**
 * Represents a player table.
 */
public class PlayerTable extends TableAdapter<PlayerRecord> {

    @Override
    public @NotNull String getName() {
        return "Player";
    }

    /**
     * Used to get a player record
     * using the player's uuid from the
     * database.
     *
     * @param uuid The players uuid.
     * @return The instance of the record.
     * Null if it doesn't exist.
     */
    public @Nullable PlayerRecord getPlayer(@NotNull UUID uuid) {
        return this.getFirstRecord(new Query().match("uuid", uuid.toString()));
    }

    /**
     * Used to get a player record
     * using the player's name from the
     * database.
     *
     * @param name The name of the player.
     * @return The instance of the record.
     * Null if it doesn't exist.
     */
    public @Nullable PlayerRecord getPlayer(@NotNull String name) {
        return this.getFirstRecord(new Query().match("name", name));
    }

    /**
     * Used to update a player's record in the
     * database.
     * Also updates there name in the record
     * if it has been changed.
     *
     * @param user The instance of the player user.
     * @return The new instance of the player record.
     */
    public @NotNull PlayerRecord updatePlayer(PlayerUser user) {
        PlayerRecord result = this.getFirstRecord(
                new Query().match("uuid", user.getUuid().toString())
        );

        // Check if the player record does not exist.
        if (result == null) {
            PlayerRecord playerRecord = new PlayerRecord();
            playerRecord.uuid = user.getUuid().toString();
            playerRecord.name = user.getName();

            this.insertRecord(playerRecord);
            return playerRecord;
        }

        // Check if the player has changed their name.
        if (!Objects.equals(result.name, user.getName())) {
            result.name = user.getName();
            this.insertRecord(result);
        }

        return result;
    }

    /**
     * Used to check if the player table contains a player name.
     *
     * @param playerName The player's name to check for.
     * @return True if the player exists in the database.
     */
    public boolean contains(String playerName) {
        PlayerRecord playerRecord = this.getFirstRecord(new Query().match("name", playerName));
        return playerRecord != null;
    }
}