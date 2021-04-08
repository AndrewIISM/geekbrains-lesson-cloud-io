package gb.cloud.server;

import gb.cloud.server.factory.Factory;
import org.sqlite.JDBC;

public class Main {

    public static void main(String[] args) {
        Factory.getServerService().startServer();
    }

}
