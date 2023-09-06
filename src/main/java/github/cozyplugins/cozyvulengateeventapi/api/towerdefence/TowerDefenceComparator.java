package github.cozyplugins.cozyvulengateeventapi.api.towerdefence;

import github.cozyplugins.cozyvulengateeventapi.api.towerdefence.record.TowerDefenceGameRecord;

import java.util.Comparator;

/**
 * Represents a utility class for generating
 * tower defense comparators.
 */
public final class TowerDefenceComparator {

    /**
     * Used to get the time comparator.
     *
     * @return The comparator for game times.
     */
    public static Comparator<TowerDefenceGameRecord> getTimeComparator() {
        return (o1, o2) -> {
            long mills1 = o1.getTimeLasted().toMillis();
            long mills2 = o2.getTimeLasted().toMillis();
            return Long.compare(mills1, mills2);
        };
    }

    /**
     * Used to get the money comparator.
     *
     * @return The comparator for game money.
     */
    public static Comparator<TowerDefenceGameRecord> getMoneyComparator() {
        return Comparator.comparingInt(o -> o.endMoney);
    }

    /**
     * Used to get the health comparator.
     *
     * @return The comparator for game base health.
     */
    public static Comparator<TowerDefenceGameRecord> getHealthComparator() {
        return (o1, o2) -> {
            int baseHealth1 = o1.baseHealth;
            int baseHealth2 = o2.baseHealth;

            // Compare health.
            int result = Integer.compare(baseHealth1, baseHealth2);

            // If the same compare time.
            if (result == 0) return getTimeComparator().compare(o1, o2);
            return result;
        };
    }
}
