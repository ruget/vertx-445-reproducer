
package ruget.reproducer.vertx.enumgen;

import java.io.IOException;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import io.vertx.core.Vertx;
import io.vertx.junit5.VertxExtension;
import io.vertx.junit5.VertxTestContext;

@ExtendWith(VertxExtension.class)
@DisplayName("Enum in service test")
public class EnumInServiceProxyTest {

  @BeforeEach
  void deploy_verticle(Vertx vertx, VertxTestContext testContext) {
    vertx.deployVerticle(MainVerticle.class.getName(), testContext.completing());
  }

  @DisplayName("CAll service proxy with an enum with toString")
  @Test
  public void updateStatusWithToString(Vertx vertx, VertxTestContext testContext) throws IOException {
    vertx.eventBus().<String>send("test.verticle", Exemple.var1.name(), rh -> {
      testContext.verify(() -> {
        Assertions.assertThat(rh.succeeded()).isTrue();
        Exemple result = Exemple.valueOf(rh.result().body());
        Assertions.assertThat(result).isSameAs(Exemple.var1);
        testContext.completeNow();
      });
    });
  }

  @DisplayName("CAll service proxy with an enum with witout toString")
  @Test
  public void updateStatus(Vertx vertx, VertxTestContext testContext) throws IOException {
    vertx.eventBus().<String>send("test.verticle.ok", ExempleOk.var1.name(), rh -> {
      testContext.verify(() -> {
        Assertions.assertThat(rh.succeeded()).isTrue();
        ExempleOk result = ExempleOk.valueOf(rh.result().body());
        Assertions.assertThat(result).isSameAs(ExempleOk.var1);
        testContext.completeNow();
      });
    });
  }

}
