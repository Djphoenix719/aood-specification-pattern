package org.spec.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Helper methods for reflection.
 */
public final class Reflect {
  /**
   * Get all declared fields in the class and it's superclasses.
   * @param type The class to get fields from.
   * @return An array of all fields.
   */
  public static Field[] getAllFields(Class<?> type) {
    return getAllFields(new ArrayList<>(), type);
  }

  private static Field[] getAllFields(List<Field> fields, Class<?> type) {
    do {
      fields.addAll(0, Arrays.asList(type.getDeclaredFields()));
      type = type.getSuperclass();
    } while (type != null);

    return fields.toArray(new Field[0]);
  }
}
