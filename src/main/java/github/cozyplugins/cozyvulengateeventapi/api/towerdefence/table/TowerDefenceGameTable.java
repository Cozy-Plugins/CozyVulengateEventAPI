package github.cozyplugins.cozyvulengateeventapi.api.towerdefence.table;

import com.github.smuddgge.squishydatabase.Query;
import com.github.smuddgge.squishydatabase.interfaces.TableAdapter;
import github.cozyplugins.cozyvulengateeventapi.api.Leaderboard;
import github.cozyplugins.cozyvulengateeventapi.api.towerdefence.record.TowerDefenceGameRecord;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * Represents the tower defense game table.
 */
public class TowerDefenceGameTable extends TableAdapter<TowerDefenceGameRecord> {

    @Override
    public @NotNull String getName() {
        return "TowerDefenceGame";
    }

    /**
     * Used to get the game record matching a specific
     * session identifier.
     *
     * @param identifier The session identifier.
     * @return The instance of the record.
     */
    public @Nullable TowerDefenceGameRecord getRecord(@NotNull String identifier) {
        return this.getFirstRecord(new Query().match("identifier", identifier));
    }

    /**
     * Used to get the leaderboard for a specific arena.
     *
     * @param arenaIdentifier The arena identifier.
     * @return The arena's leaderboard.
     */
    public @NotNull Leaderboard<TowerDefenceGameRecord> getLeaderboardArena(@NotNull String arenaIdentifier) {
        Leaderboard<TowerDefenceGameRecord> leaderboard = new Leaderboard<>();
        leaderboard.add(this.getRecordList(new Query().match("arenaIdentifier", arenaIdentifier)));
        return leaderboard;
    }

    /**
     * Used to get a leaderboard for a specific list of arenas.
     *
     * @param arenaIdentifiers The list of arena identifiers.
     * @return The instance of the leaderboard.
     */
    public @NotNull Leaderboard<TowerDefenceGameRecord> getLeaderboardArena(@NotNull List<String> arenaIdentifiers) {
        Leaderboard<TowerDefenceGameRecord> leaderboard = new Leaderboard<>();

        for (String arena : arenaIdentifiers) {
            leaderboard.add(this.getRecordList(new Query().match("arenaIdentifier", arena)));
        }

        return leaderboard;
    }
}
