package org.spec.specification;

import org.spec.container.Container;

import java.util.List;

/**
 * Supports generic operations to select based on a specified criteria.
 */
public interface ISpecification {
  /**
   * Returns true if the container satisfies the specifications conditions.
   *
   * @param container The container to validate.
   * @return True or false.
   */
  boolean isSatisfiedBy(Container container);

  /**
   * Returns true if a specification is subsumed the provide specification.
   *
   * @param specification The specification to validate.
   * @return True or false.
   */
  boolean isGeneralizationOf(ISpecification specification);

  /**
   * Returns a new specification that is a combination of this specification and
   * all other provided specifications.
   *
   * @param others Others to perform the and operation on.
   * @return The combined specification.
   */
  ISpecification and(ISpecification... others);

  /**
   * Returns a new specification that is a combination of this specification and
   * all other provided specifications, and will return true if any of them are
   * satisfied.
   *
   * @param others Others to perform the or operation on.
   * @return The combined specification.
   */
  ISpecification or(ISpecification... others);

  /**
   * Returns a new specification which contains operations that were not satisfied
   * by the specified container.
   *
   * @param container The container to validate.
   * @return A new specification.
   */
  ISpecification remainderUnsatisfiedBy(Container container);

  /**
   * The list of children specifications contained in this specification.
   *
   * @return A list of specifications (may be empty).
   */
  List<? extends ISpecification> getChildren();

  /**
   * Add one or more specifications to this specification.
   *
   * @param children The children specification to add.
   */
  void addChildren(ISpecification... children);

  /**
   * Remove one or more specifications from this specification.
   *
   * @param children The children specification to remove.
   */
  void removeChildren(ISpecification... children);
}
