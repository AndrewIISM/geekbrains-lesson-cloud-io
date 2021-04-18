package gb.cloud;


import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.stream.ChunkedFile;
import io.netty.handler.stream.ChunkedWriteHandler;

import java.io.File;
import java.io.IOException;

public class ClientMain {

    private SocketChannel channel;

    private static final String HOST = "localhost";
    private static final int PORT = 8189;

    public static void main(String[] args) throws IOException, InterruptedException {
        ClientMain clientMain = new ClientMain();
        clientMain.start();

        Thread.sleep(2000);
        System.out.println("Start write");

        ChannelFuture future = clientMain.channel.writeAndFlush(new ChunkedFile(new File("input-data/2021-04-12 19-05-19.mkv")));
        future.addListener((ChannelFutureListener) channelFuture -> System.out.println("Finish write"));
    }

    private void start() {
        Thread t = new Thread(() -> {
            NioEventLoopGroup workerGroup = new NioEventLoopGroup();
            try {
                Bootstrap b = new Bootstrap();
                b.group(workerGroup)
                        .channel(NioSocketChannel.class)
                        .handler(new ChannelInitializer<SocketChannel>() {
                            @Override
                            protected void initChannel(SocketChannel socketChannel) {
                                channel = socketChannel;
                                socketChannel.pipeline().addLast(
                                    new ChunkedWriteHandler()
                                );
                            }
                        });
                ChannelFuture future = b.connect(HOST, PORT).sync();
                future.channel().closeFuture().sync();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                workerGroup.shutdownGracefully();
            }
        });
        t.start();
    }

}
