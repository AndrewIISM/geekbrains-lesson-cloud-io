package gb.cloud.server.service;

import gb.cloud.domain.Command;

public interface CommandService {

    String processCommand(Command command);

    String getCommand();

}
