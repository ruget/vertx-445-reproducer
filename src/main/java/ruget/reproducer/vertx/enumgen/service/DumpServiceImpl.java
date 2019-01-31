package ruget.reproducer.vertx.enumgen.service;

import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import ruget.reproducer.vertx.enumgen.Exemple;
import ruget.reproducer.vertx.enumgen.ExempleOk;

public class DumpServiceImpl implements DumpService {

  public DumpServiceImpl(Vertx vertx, Handler<AsyncResult<DumpService>> readyHandler) {
    readyHandler.handle(Future.succeededFuture());
  }

  @Override
  public DumpService test(Exemple ex, Handler<AsyncResult<Exemple>> resultHandler) {
    resultHandler.handle(Future.succeededFuture(ex));
    return this;
  };

  @Override
  public DumpService testOk(ExempleOk ex, Handler<AsyncResult<ExempleOk>> resultHandler) {
    resultHandler.handle(Future.succeededFuture(ex));
    return this;
  };
}