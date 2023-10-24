package seedu.address.logic.commands.statscalculator;

import seedu.address.model.studentscore.StudentScore;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
        return getSum()/scores.size();
    }

    public float getStd() {
        return getSumOfSquare()/scores.size() - getMean()*getMean();
    }
}
