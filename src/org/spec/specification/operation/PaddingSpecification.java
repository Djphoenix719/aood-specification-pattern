package org.spec.specification.operation;

import org.spec.container.Container;

/**
 * Checks for the presence of padding on the container.
 */
public class PaddingSpecification extends SpecificationOperation {
  @Override
  public boolean isSatisfiedBy(Container container) {
    return container.isPadded();
  }
}
