package ruget.reproducer.vertx.enumgen.service;

import io.vertx.codegen.annotations.Fluent;
import io.vertx.codegen.annotations.GenIgnore;
import io.vertx.codegen.annotations.ProxyGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import ruget.reproducer.vertx.enumgen.Exemple;
import ruget.reproducer.vertx.enumgen.ExempleOk;

@ProxyGen
public interface DumpService {

  public static final String ADDRESS = "ruget.reproducer.vertx.enumgen.service.dumb";

  @GenIgnore
  static DumpService create(Vertx vertx, Handler<AsyncResult<DumpService>> readyHandler) {
    return new DumpServiceImpl(vertx, readyHandler);
  }

  @GenIgnore
  static DumpService createProxy(Vertx vertx, String address) {
    return new DumpServiceVertxEBProxy(vertx, address);
  }

  @Fluent
  DumpService test(Exemple ex, Handler<AsyncResult<Exemple>> resultHandler);

  @Fluent
  DumpService testOk(ExempleOk ex, Handler<AsyncResult<ExempleOk>> resultHandler);

}
