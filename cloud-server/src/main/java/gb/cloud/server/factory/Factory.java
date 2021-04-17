package gb.cloud.server.factory;

import gb.cloud.server.service.CommandDictionaryService;
import gb.cloud.server.service.CommandService;
import gb.cloud.server.service.ServerService;
import gb.cloud.server.service.impl.CommandDictionaryServiceImpl;
import gb.cloud.server.service.impl.NettyServerService;
import gb.cloud.server.service.impl.command.ViewFilesInDirCommand;

import java.util.Arrays;
import java.util.List;

public class Factory {

    public static ServerService getServerService() {
        return new NettyServerService();
    }

    public static CommandDictionaryService getCommandDirectoryService() {
        return new CommandDictionaryServiceImpl();
    }

    public static List<CommandService> getCommandServices() {
        return Arrays.asList(new ViewFilesInDirCommand());
    }

}
