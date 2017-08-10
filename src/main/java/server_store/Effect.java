package server_store;

import common.StoreAction;

/**
 * Created by giorgiopea on 08/03/17.
 *
 */
public interface Effect {

    void apply(StoreAction action, State state);

}
