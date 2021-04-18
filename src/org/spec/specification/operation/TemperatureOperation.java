package org.spec.specification.operation;

import org.spec.container.Container;

/**
 * Checks for logical equality of temperature.
 */
public class TemperatureOperation extends EqualityOperation {
  private final double temperature;

  public TemperatureOperation(double temperature, EqualityComparison comparison) {
    super(comparison);
    this.temperature = temperature;
  }

  @Override
  public boolean isSatisfiedBy(Container container) {
    return this.compare(container.getTemperature(), this.temperature);
  }
}
