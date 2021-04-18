package org.spec.specification.operation;

import org.spec.container.Container;

/**
 * Checks for the presence of sanitation on a container.
 */
public class SanitationOperation extends SpecificationOperation {
  @Override
  public boolean isSatisfiedBy(Container container) {
    return container.isSanitary();
  }
}
