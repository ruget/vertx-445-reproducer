package ruget.reproducer.vertx.enumgen;

public enum Exemple {

  var1, var2;

  @Override
  public String toString() {
    return "my pretty print for enum name " + this.name();
  }

}
