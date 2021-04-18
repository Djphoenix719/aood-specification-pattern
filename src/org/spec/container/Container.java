package org.spec.container;

import org.spec.util.Reflect;

import java.util.Arrays;
import java.util.UUID;
import java.util.stream.Collectors;

public class Container {
  private final UUID id;
  private final boolean sanitary;
  private final double temperature;
  private final boolean padded;
  private final double maximumWeight;

  public Container(
      UUID id, boolean sanitary, double temperature, boolean padded, double maximumWeight) {
    this.id = id;
    this.sanitary = sanitary;
    this.temperature = temperature;
    this.padded = padded;
    this.maximumWeight = maximumWeight;
  }

  public UUID getId() {
    return id;
  }

  public boolean isSanitary() {
    return sanitary;
  }

  public double getTemperature() {
    return temperature;
  }

  public boolean isPadded() {
    return padded;
  }

  public double getMaximumWeight() {
    return maximumWeight;
  }

  @Override
  public String toString() {
    StringBuilder result = new StringBuilder(this.getClass().getName());
    result.append("{");

    var kvPairs =
        Arrays.stream(Reflect.getAllFields(this.getClass()))
            .map(
                (var f) -> {
                  try {
                    f.setAccessible(true);
                    return String.format("%s=\"%s\"", f.getName(), f.get(this));
                  } catch (IllegalAccessException exception) {
                    return null;
                  }
                })
            .collect(Collectors.toList());

    result.append(String.join(", ", kvPairs));
    result.append("}");
    return result.toString();
  }
}
