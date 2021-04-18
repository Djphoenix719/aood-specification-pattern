package org.spec.cargo;

import org.spec.specification.ISpecification;

public class Cargo {
  private ISpecification specification;

  public Cargo(ISpecification specification) {
    this.specification = specification;
  }

  public ISpecification getSpecification() {
    return specification;
  }

  public void setSpecification(ISpecification specification) {
    this.specification = specification;
  }
}
