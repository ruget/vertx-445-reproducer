package ruget.reproducer.vertx.enumgen;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Launcher;
import io.vertx.serviceproxy.ServiceBinder;
import ruget.reproducer.vertx.enumgen.service.DumpService;

public class MainVerticle extends AbstractVerticle {

  public static void main(String[] args) {
    Launcher.executeCommand("run", MainVerticle.class.getName());
  }

  @Override
  public void start(Future<Void> startFuture) {
    Future<DumpService> future = Future.future();
    final DumpService service = DumpService.create(vertx, future.completer());

    future.setHandler(h -> {
      if (h.succeeded()) {
        new ServiceBinder(vertx).setAddress(DumpService.ADDRESS).register(DumpService.class, service);
        vertx.deployVerticle(TestVerticle.class.getName());
        startFuture.handle(Future.succeededFuture());
      } else {
        startFuture.handle(Future.failedFuture(h.cause()));
      }
    });
  }
}
