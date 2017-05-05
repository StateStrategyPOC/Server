package server_store;

/**
 * Created by giorgiopea on 08/03/17.
 *
 */
public interface Effect {

    void apply(StoreAction action, State state);

}
