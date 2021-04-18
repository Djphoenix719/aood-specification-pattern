package org.spec;

import org.spec.cargo.Cargo;
import org.spec.container.Container;
import org.spec.specification.ISpecification;
import org.spec.specification.NotSpecification;
import org.spec.specification.OrSpecification;
import org.spec.specification.Specification;
import org.spec.specification.operation.*;

import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;

public class App {

  public static Container makeExampleContainer() {
    Random random = new Random();
    return new Container(
            UUID.randomUUID(),
            random.nextBoolean(),
            random.nextDouble() * 100.0,
            random.nextBoolean(),
            random.nextDouble() * 20_000.0);
  }

  public static void main(String[] args) {
    // We'll use a set of 100,000 randomly generated containers to ensure a good
    //  statistical distribution for demonstration purposes.
    ArrayList<Container> containers = new ArrayList<>();
    for (int i = 0; i < 30_000; i++) {
      containers.add(makeExampleContainer());
    }

    example1(containers);
    example2(containers);
  }

  public static void example1(ArrayList<Container> containers) {
    System.out.println("-".repeat(100));
    System.out.println("Example 1");
    System.out.println("All containers must...");
    System.out.println("\t    Support a weight of >= 15,000 lbs.");
    System.out.println("\tAND Be sanitary.");
    System.out.println("-".repeat(100));

    ISpecification spec = new Specification();
    spec.addChildren(new WeightOperation(15_000, EqualityComparison.GreaterThanEqualTo));
    spec.addChildren(new SanitationOperation());

    Cargo cargo = new Cargo(spec);

    for (Container container : containers) {
      if (cargo.getSpecification().isSatisfiedBy(container)) {
        System.out.println(container);
      }
    }
  }

  public static void example2(ArrayList<Container> containers) {
    System.out.println("-".repeat(100));
    System.out.println("Example 1");
    System.out.println("All containers must...");
    System.out.println("\tSupport a weight of <= 5,000 lbs.");
    System.out.println("\t\tAND NOT");
    System.out.println("\t\t\t   Have a temperature > 25 degrees.");
    System.out.println("\t\t\tOR Have a temperature < 20 degrees.");
    System.out.println("-".repeat(100));

    ISpecification spec = new Specification();
    spec.addChildren(new WeightOperation(5_000, EqualityComparison.LessThanEqualTo));

    ISpecification notSpec = new NotSpecification(new OrSpecification(
            new TemperatureOperation(25, EqualityComparison.GreaterThan),
            new TemperatureOperation(20, EqualityComparison.LessThan)
    ));
    spec.addChildren(notSpec);

    Cargo cargo = new Cargo(spec);

    for (Container container : containers) {
      if (cargo.getSpecification().isSatisfiedBy(container)) {
        System.out.println(container);
      }
    }
  }
}
