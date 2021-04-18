package gb.cloud.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class BigFilesWriteHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object chunkedFile) {
        ByteBuf byteBuf = (ByteBuf) chunkedFile;

        try (OutputStream os = new BufferedOutputStream(new FileOutputStream("data/2021-04-12 19-05-19.mkv", true))) {
            while (byteBuf.isReadable()) {
                os.write(byteBuf.readByte());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        byteBuf.release();
    }
}
