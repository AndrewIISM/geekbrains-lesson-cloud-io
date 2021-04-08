package gb.cloud.server.factory;

import gb.cloud.server.service.ClientService;
import gb.cloud.server.service.CommandDictionaryService;
import gb.cloud.server.service.CommandService;
import gb.cloud.server.service.ServerService;
import gb.cloud.server.service.impl.CommandDictionaryServiceImpl;
import gb.cloud.server.service.impl.IOClientService;
import gb.cloud.server.service.impl.SocketServerService;
import gb.cloud.server.service.impl.command.ViewFilesInDirCommand;

import java.net.Socket;
import java.util.Arrays;
import java.util.List;

public class Factory {

    public static ServerService getServerService() {
        return SocketServerService.getInstance();
    }

    public static ClientService getClientService(Socket socket) {
        return new IOClientService(socket);
    }

    public static CommandDictionaryService getCommandDirectoryService() {
        return new CommandDictionaryServiceImpl();
    }

    public static List<CommandService> getCommandServices() {
        return Arrays.asList(new ViewFilesInDirCommand());
    }

}
