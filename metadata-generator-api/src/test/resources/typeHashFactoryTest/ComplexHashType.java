package typeHashFactoryTest;

@Hash
public class ComplexHashType {
  private TypeWithEnum typeWithEnum;

  private String stringValue;

  private InnerType innerType;

  public class InnerType {
    private String stringValue;

    private Integer integerValue;
  }
}
