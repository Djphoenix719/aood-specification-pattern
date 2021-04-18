package org.spec.specification;

import org.spec.container.Container;

public class OrSpecification extends Specification {

  public OrSpecification(ISpecification... others) {
    super();

    addChildren(others);
  }

  @Override
  public boolean isSatisfiedBy(Container container) {
    for (var child : children) {
      if (child.isSatisfiedBy(container)) {
        return true;
      }
    }
    return false;
  }
}
