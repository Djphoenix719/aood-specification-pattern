package org.spec.specification.operation;

/**
 * Performs a logical equality operation on a double value.
 */
public abstract class EqualityOperation extends SpecificationOperation {
  private final EqualityComparison comparison;

  public EqualityOperation(EqualityComparison comparison) {
    this.comparison = comparison;
  }

  /**
   * Compare two values based on the initialized operator.
   * @param a The left hand value.
   * @param b The right hand value.
   * @return True or false result of the operation.
   */
  protected final boolean compare(double a, double b) {
    switch (this.comparison) {
      case EqualTo -> {
        return a == b;
      }
      case LessThan -> {
        return a < b;
      }
      case LessThanEqualTo -> {
        return a <= b;
      }
      case GreaterThan -> {
        return a > b;
      }
      case GreaterThanEqualTo -> {
        return a >= b;
      }
      default -> {
        return false;
      }
    }
  }
}
