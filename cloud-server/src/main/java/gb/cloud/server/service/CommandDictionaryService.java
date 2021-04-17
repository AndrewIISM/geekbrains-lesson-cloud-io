package gb.cloud.server.service;

import gb.cloud.domain.Command;

public interface CommandDictionaryService {

    String processCommand(Command command);

}
