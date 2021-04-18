package org.spec.specification;

import org.spec.container.Container;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class Specification implements ISpecification {
  protected Specification parent;
  protected List<Specification> children;

  public Specification() {
    this.children = new ArrayList<>();
  }

  public Specification(ISpecification... children) {
    this.children = new ArrayList<>();

    addChildren(children);
  }

  public Specification getParent() {
    return parent;
  }

  public void setParent(Specification parent) {
    this.parent = parent;
  }

  @Override
  public List<? extends ISpecification> getChildren() {
    return this.children;
  }

  @Override
  public boolean isSatisfiedBy(Container container) {
    for (var child : children) {
      if (!child.isSatisfiedBy(container)) {
        return false;
      }
    }
    return true;
  }

  @Override
  public boolean isGeneralizationOf(ISpecification specification) {
    // Sophisticated implementation, not pictured here.
    return false;
  }

  @Override
  public ISpecification and(ISpecification... others) {
    Specification newSpecification = new Specification();
    newSpecification.addChildren((ISpecification) children);
    for (var specification : others) {
      newSpecification.addChildren((ISpecification) specification.getChildren());
    }
    return newSpecification;
  }

  @Override
  public ISpecification or(ISpecification... others) {
    ISpecification newSpecification = new OrSpecification();
    newSpecification.addChildren((ISpecification) children);
    for (var specification : others) {
      newSpecification.addChildren((ISpecification) specification.getChildren());
    }
    return newSpecification;
  }

  @Override
  public ISpecification remainderUnsatisfiedBy(Container container) {
    ISpecification remaining = new Specification();
    for (var child : children) {
      if (!child.isSatisfiedBy(container)) {
        remaining.addChildren(child);
      }
    }
    return remaining;
  }

  public void addChild(ISpecification child) {
    this.children.add((Specification) child);
    ((Specification) child).setParent(this);
  }

  @Override
  public void addChildren(ISpecification... children) {
    Objects.requireNonNull(children);
    Stream.of(children).forEach(this::addChild);
  }

  public void removeChild(ISpecification child) {
    this.children.remove(child);
    ((Specification) child).setParent(null);
  }

  @Override
  public void removeChildren(ISpecification... children) {
    Objects.requireNonNull(children);
    Stream.of(children).forEach(this::removeChild);
  }
}
