package github.cozyplugins.cozyvulengateeventapi.towerdefence;

import github.cozyplugins.cozyvulengateeventapi.towerdefence.record.TowerDefenceGameRecord;

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
        return Comparator.comparingInt(o -> o.baseHealth);
    }
}
