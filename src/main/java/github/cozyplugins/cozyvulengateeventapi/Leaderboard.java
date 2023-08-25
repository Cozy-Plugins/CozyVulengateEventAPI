package github.cozyplugins.cozyvulengateeventapi;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Represents a type of leaderboard.
 *
 * @param <T> The object listed in the leaderboard.
 */
public class Leaderboard<T> {

    private final @NotNull List<T> list;
    private @NotNull Comparator<T> comparator;
    private @NotNull Format<T> format;

    /**
     * Used to create a new instance of a leaderboard.
     */
    public Leaderboard() {
        this.list = new ArrayList<>();
        this.comparator = (o1, o2) -> 0;

        // Create a default format.
        this.format = new Format<T>() {
            @Override
            public @NotNull String getFormat(@NotNull T object, int rank) {
                return rank + ") " + object;
            }
        };
    }

    /**
     * Represents a format interface.
     * Used to format a line in the leaderboard.
     *
     * @param <T> The type of object being formatted.
     */
    public interface Format<T> {

        /**
         * Used to format a line in the leaderboard.
         *
         * @param object The instance of the object.
         * @param rank The rank of the object.
         * @return The line to add to the leaderboard.
         */
        @NotNull String getFormat(@NotNull T object, int rank);
    }

    /**
     * Used to get an object with a certain
     * rank on the leaderboard.
     * <li>Starts at rank 1.</li>
     *
     * @param rank The rank to get.
     * @return The requested object.
     * Null if the rank does not exist.
     */
    public @Nullable T get(int rank) {

        // Sort list.
        this.list.sort(this.comparator);

        // Check if its in range.
        if (this.list.size() < rank) return null;

        // Get the instance of the object.
        return this.list.get(rank - 1);
    }

    /**
     * Used to get a rank as a formatted string.
     *
     * @param rank The rank to get.
     * @return The requested formatted rank.
     */
    public @NotNull String getFormatted(int rank) {

        // Get object.
        T object = this.get(rank);

        // Check if the object is null.
        if (object == null) return "None";

        // Get the object formatted.
        return this.format.getFormat(object, rank);
    }

    /**
     * Used to get the ranks formatted.
     *
     * @param from The first rank to include.
     * @param to The last rank to include.
     * @return The instance of the list.
     */
    public @NotNull List<String> getFormattedList(int from, int to) {
        List<String> list = new ArrayList<>();
        int current = from;

        while (current <= to) {
            list.add(this.getFormatted(current));
            current++;
        }

        return list;
    }

    /**
     * Used to set the format of the leaderboard.
     * This will be used when parsing lines
     * of the leaderboard into strings.
     *
     * @param format The format to set.
     * @return This instance.
     */
    public @NotNull Leaderboard<T> setFormat(@NotNull Format<T> format) {
        this.format = format;
        return this;
    }

    /**
     * Used to set the comparator.
     * This is used to order the leaderboard.
     *
     * @param comparator The instance of the comparator.
     * @return This instance.
     */
    public @NotNull Leaderboard<T> setComparator(@NotNull Comparator<T> comparator) {
        this.comparator = comparator;
        return this;
    }

    /**
     * Used to add an object to the leaderboard.
     *
     * @param object The object to add.
     * @return This instance.
     */
    public @NotNull Leaderboard<T> add(@NotNull T object) {
        this.list.add(object);
        return this;
    }

    /**
     * Used to add a list of objects.
     *
     * @param objectList The list of objects.
     * @return This instance.
     */
    public @NotNull Leaderboard<T> add(@NotNull List<T> objectList) {
        this.list.addAll(objectList);
        return this;
    }
}
