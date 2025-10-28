package main.java.com.filippovich.arrayapp.repository.specification;

import main.java.com.filippovich.arrayapp.entity.StringArray;
import main.java.com.filippovich.arrayapp.repository.Specification;
import main.java.com.filippovich.arrayapp.warehouse.impl.ArrayStatisticsImpl;
import main.java.com.filippovich.arrayapp.warehouse.impl.Warehouse;

import java.util.Optional;

public class MaxLengthSpecification implements Specification {

    private final int targetMaxLength;

    public MaxLengthSpecification(int targetMaxLength) {
        this.targetMaxLength = targetMaxLength;
    }

    @Override
    public boolean specified(StringArray array) {
        Warehouse warehouse = Warehouse.getInstance();
        Optional<ArrayStatisticsImpl> stats = warehouse.getStatistics(array.getId());
        return stats.isPresent() && stats.get().getMaxLength() == targetMaxLength;
    }
}