package seedu.address.logic.commands.statscalculator;

import java.util.List;

/**
 * Calculates the statics.
 */
public class StatsCalculator {
    private final List<Float> scores;

    public StatsCalculator(List<Float> scores) {
        this.scores = scores;
    }


    public float getMax() {
        float max = Float.MIN_VALUE;
        for (float score : scores) {
            max = Math.max(max, score);
        }
        return max;
    }

    public float getMin() {
        float min = Float.MAX_VALUE;
        for (float score : scores) {
            min = Math.min(min, score);
        }
        return min;
    }

    private float getSum() {
        float sum = 0;
        for (float score : scores) {
            sum += score;
        }
        return sum;
    }

    private float getSumOfSquare() {
        float sum = 0;
        for (float score : scores) {
            sum += score * score;
        }
        return sum;
    }

    public float getMean() {
        return getSum() / scores.size();
    }

    public float getStd() {
        return (float) Math.pow(getSumOfSquare() / scores.size() - getMean() * getMean(), 0.5);
    }
}
