package org.spec.specification;

import org.spec.container.Container;

public class NotSpecification extends Specification {

  public NotSpecification(ISpecification... others) {
    super();

    addChildren(others);
  }

  @Override
  public boolean isSatisfiedBy(Container container) {
    return !super.isSatisfiedBy(container);
  }
}
