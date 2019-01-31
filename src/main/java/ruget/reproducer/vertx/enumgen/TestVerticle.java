package ruget.reproducer.vertx.enumgen;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.vertx.core.AbstractVerticle;
import ruget.reproducer.vertx.enumgen.service.DumpService;

public class TestVerticle extends AbstractVerticle {

  private final static Logger LOG = LoggerFactory.getLogger(TestVerticle.class);

  @Override
  public void start() throws Exception {
    DumpService service = DumpService.createProxy(vertx, DumpService.ADDRESS);

    vertx.eventBus().<String>consumer("test.verticle", h -> {
      service.test(Exemple.valueOf(h.body()), r -> {
        LOG.info("Service return {}", r.succeeded());
        if (r.succeeded()) {
          h.reply(r.result().name());
        } else {
          h.fail(0, r.cause().getLocalizedMessage());
        }
      });
    });

    vertx.eventBus().<String>consumer("test.verticle.ok", h -> {
      service.testOk(ExempleOk.valueOf(h.body()), r -> {
        LOG.info("Service return {}", r.succeeded());
        if (r.succeeded()) {
          h.reply(r.result().name());
        } else {
          h.fail(0, r.cause().getLocalizedMessage());
        }
      });
    });
  }

}
