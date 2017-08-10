package server_store_actions;

import server.ReqRespHandler;
import common.StoreAction;

public class ServerAddReqRespHandlerAction extends StoreAction {

    private final ReqRespHandler handler;

    public ServerAddReqRespHandlerAction(ReqRespHandler handler) {
        super("@SERVER_ADD_REQRESP_HANDLER","@SERVER_GROUP");
        this.handler = handler;
    }

    public ReqRespHandler getHandler() {
        return handler;
    }
}
