package server_store;

import common.StoreAction;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class StoreLogger {
    private Logger logger;
    private  static StoreLogger instance;

    public static StoreLogger getInstance(){
        if (instance == null){
            instance = new StoreLogger();
        }
        return instance;
    }

    private StoreLogger(){
        this.logger = Logger.getLogger("STORE_LOGGER");
        this.initLogger();
    }

    private void initLogger() {
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        String formattedDate = dateFormat.format(date);
        File file = new File(formattedDate+".log");
        if (!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            FileHandler fh = new FileHandler(formattedDate+".log",true);
            SimpleFormatter simpleFormatter = new SimpleFormatter();
            fh.setFormatter(simpleFormatter);
            this.logger.addHandler(fh);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void logPreStatePropagation(StoreAction action, ServerState state){
        Date date = new Date();
        this.logger.log(Level.OFF, "@@BEGIN_ITEM@@");
        this.logger.log(Level.OFF, "@@PRE_TIMESTAMP@@");
        this.logger.log(Level.OFF, date.getTime()+"");
        this.logger.log(Level.OFF, "@@PRE_STATE@@");
        this.logger.log(Level.OFF, state.toString());
        this.logger.log(Level.OFF, "@@ACTION@@");
        this.logger.log(Level.OFF, action.toString());
    }
    public void logPostStatePropagation(ServerState state){
        Date date = new Date();
        this.logger.log(Level.INFO, "@@POST_TIMESTAMP@@");
        this.logger.log(Level.INFO, date.getTime()+"");
        this.logger.log(Level.INFO, "@@POST_STATE@@");
        this.logger.log(Level.INFO, state.toString());
        this.logger.log(Level.INFO, "@@END_ITEM@@");
    }

}
