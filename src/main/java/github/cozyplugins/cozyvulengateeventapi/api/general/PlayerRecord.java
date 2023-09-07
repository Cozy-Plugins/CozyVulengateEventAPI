package github.cozyplugins.cozyvulengateeventapi.api.general;

import com.github.smuddgge.squishydatabase.record.Record;
import com.github.smuddgge.squishydatabase.record.RecordFieldAnnotation;
import com.github.smuddgge.squishydatabase.record.RecordFieldType;
import org.jetbrains.annotations.NotNull;

/**
 * Represents a player record.
 */
public class PlayerRecord extends Record {

    @RecordFieldAnnotation(type = RecordFieldType.PRIMARY)
    public String uuid;

    public String name;

    public String color;

    /**
     * Used to get the player's realm color.
     *
     * @return The realm color.
     */
    public @NotNull String getColor() {
        return this.color == null ? "None" : this.color;
    }
}