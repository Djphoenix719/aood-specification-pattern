package org.spec.specification.operation;

import org.spec.container.Container;

/**
 * Checks for logical equality of temperature.
 */
public class WeightOperation extends EqualityOperation {
  private final double weight;

  public WeightOperation(double weight, EqualityComparison operation) {
    super(operation);

    this.weight = weight;
  }

  @Override
  public boolean isSatisfiedBy(Container container) {
    return this.compare(container.getMaximumWeight(), this.weight);
  }
}
