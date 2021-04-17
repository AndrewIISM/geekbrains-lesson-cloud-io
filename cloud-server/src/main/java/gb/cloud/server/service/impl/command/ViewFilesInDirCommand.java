package gb.cloud.server.service.impl.command;

import gb.cloud.domain.Command;
import gb.cloud.server.service.CommandService;

import java.io.File;

public class ViewFilesInDirCommand implements CommandService {

    @Override
    public String processCommand(Command command) {
        final int requirementCountCommandArgs = 1;

        if (command.getArgs().length != requirementCountCommandArgs) {
            throw new IllegalArgumentException("Command \"" + getCommand() + "\" is not correct");
        }

        return process(command.getArgs()[0]);
    }

    private String process(String dirPath) {
        File directory = new File(dirPath);

        if (!directory.exists()) {
            return "Directory is not exists";
        }

        StringBuilder builder = new StringBuilder();
        for (File childFile : directory.listFiles()) {
            String typeFile = getTypeFile(childFile);
            builder.append(childFile.getName()).append(" | ").append(typeFile).append(System.lineSeparator());
        }

        return builder.toString();
    }

    private String getTypeFile(File childFile) {
        return childFile.isDirectory() ? "DIR" : "FILE";
    }

    @Override
    public String getCommand() {
        return "ls";
    }

}
