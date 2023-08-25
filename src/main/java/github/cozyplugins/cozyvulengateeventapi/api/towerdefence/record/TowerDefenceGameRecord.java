package github.cozyplugins.cozyvulengateeventapi.api.towerdefence.record;

import com.github.smuddgge.squishydatabase.interfaces.Database;
import com.github.smuddgge.squishydatabase.record.Record;
import com.github.smuddgge.squishydatabase.record.RecordFieldAnnotation;
import com.github.smuddgge.squishydatabase.record.RecordFieldType;
import github.cozyplugins.cozyvulengateeventapi.api.VulengateEventsAPI;
import github.cozyplugins.cozyvulengateeventapi.api.general.PlayerRecord;
import github.cozyplugins.cozyvulengateeventapi.api.general.PlayerTable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Represents a game played in tower defense.
 */
public class TowerDefenceGameRecord extends Record {

    @RecordFieldAnnotation(type = RecordFieldType.PRIMARY)
    public String identifier;

    public String startTimeStamp;

    public String endTimeStamp;

    /**
     * The list of players broken up by commas.
     * <pre>
     *     Example:
     *     Smudge,Joe,Bob
     * </pre>
     */
    public String playerList;

    public String arenaIdentifier;

    public String sessionType;

    public String economySpeed;

    public String waveCollectionIdentifier;

    public int baseHealth;

    public int baseMaxHealth;

    public int endMoney;

    /**
     * Used to get the amount of time the
     * session lasted for.
     *
     * @return The duration of the session.
     */
    public @NotNull Duration getTimeLasted() {
        return Duration.ofMillis(
                Long.parseLong(endTimeStamp) - Long.parseLong(startTimeStamp)
        );
    }

    /**
     * Used to get the list of player names.
     *
     * @return The list of player names.
     */
    public @NotNull List<String> getPlayerNameList() {
        if (playerList == null) return new ArrayList<>();
        return new ArrayList<>(Arrays.asList(playerList.split(",")));
    }

    /**
     * Used to get the list of players in this record.
     *
     * @return The list of players as player records.
     */
    public @NotNull List<@Nullable PlayerRecord> getPlayerRecordList() {
        Database database = VulengateEventsAPI.getDatabase();
        if (database == null) return new ArrayList<>();

        // Get the player table.
        PlayerTable playerTable = database.getTable(PlayerTable.class);

        // Create new record list instance.
        List<@Nullable PlayerRecord> recordList = new ArrayList<>();

        // Loop though the player's names.
        for (String name : this.getPlayerNameList()) {
            recordList.add(playerTable.getPlayer(name));
        }

        return recordList;
    }

    /**
     * Used to add a player to the list of names.
     *
     * @param name The player name to add.
     * @return This instance.
     */
    public @NotNull TowerDefenceGameRecord addPlayer(@NotNull String name) {
        List<String> nameList = this.getPlayerNameList();
        nameList.add(name);
        playerList = String.join(",", nameList);
        return this;
    }
}
