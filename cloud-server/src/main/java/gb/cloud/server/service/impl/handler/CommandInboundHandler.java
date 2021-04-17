package gb.cloud.server.service.impl.handler;

import gb.cloud.server.factory.Factory;
import gb.cloud.server.service.CommandDictionaryService;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class CommandInboundHandler extends SimpleChannelInboundHandler<String> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String command) {
        CommandDictionaryService dictionaryService = Factory.getCommandDirectoryService();
        System.out.println(command);
        String commandResult = dictionaryService.processCommand(command);

        ctx.writeAndFlush(commandResult);
    }

}
