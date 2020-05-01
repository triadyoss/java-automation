package typeHashFactoryTest;

@Hash
public class TypeWithEnum {
  private TypeEnum enumValue;

  public static enum TypeEnum{
    ONE, TWO, THREE;
  }
}