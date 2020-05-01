package triady.generator.type.interfaces;

import com.google.common.collect.Maps;
import compozitor.processor.core.interfaces.FieldModel;
import compozitor.processor.core.interfaces.PackageModel;
import compozitor.processor.core.interfaces.TypeModel;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class TypeHashFactory {

  public static TypeHash create(TypeModel typeModel) {
    TypeHash typeHash =  TypeHash.create();
    typeModel.getFields().forEach(field -> {
      add(field, typeHash);
    });

    return typeHash;
  }

  private static void add(FieldModel field, TypeHash typeHash) {
    String property = field.getName();
    TypeModel type = field.getType();
    set(property, type, typeHash);
  }

  private static void set(String property, TypeModel value, TypeHash typeHash) {
    if (simpleType(value)) {
      typeHash.set(property, value.getName());
      return;
    }

    if (value.isEnum()) {
      List<String> values = value.getConstants().stream().map(constant -> constant.getName()).collect(Collectors.toList());
      typeHash.set(property, values);
      return;
    }

    TypeHash valueTypeHash = TypeHash.create();
    typeHash.set(property, valueTypeHash);
    value.getFields().forEach(field -> {
      add(field, valueTypeHash);
    });
  }

  private static boolean simpleType(TypeModel typeModel) {
    PackageModel packageModel = typeModel.getPackage();
    for (String packageName : Arrays.asList("java.lang", "java.util", "java.math", "java.sql")) {
      if (packageModel.getName().startsWith(packageName)) {
        return true;
      }
    }
    return false;
  }

}
