package triady.generator.core.interfaces;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE, staticName = "create")
@EqualsAndHashCode
public class Id {
  private final UUID value;

  public static Id create(Object... args){
    if(args == null){
      return Id.create(UUID.randomUUID());
    }
    String uniqueValue = Arrays.asList(args).stream().map(arg -> arg.toString()).collect(Collectors.joining("."));
    return Id.create(UUID.fromString(uniqueValue));
  }

  @Override
  public String toString() {
    return this.value.toString();
  }
}
