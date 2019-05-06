import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class JavaNioServer {

    public static void main(String[] args) throws Exception {        
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.socket().bind(new InetSocketAddress(9999));

        serverSocketChannel.configureBlocking(false);

        Selector selector = Selector.open();
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        System.out.println("Started");
        while (true) {

            int select = selector.select();
            if (select == 0) continue;

            Set<SelectionKey> selectedKeys = selector.selectedKeys();
            Iterator<SelectionKey> keyIterator = selectedKeys.iterator(); 
            while (keyIterator.hasNext()) {

                SelectionKey selectionKey = keyIterator.next();

                if (selectionKey.isAcceptable()) {
                    ServerSocketChannel channel = (ServerSocketChannel) selectionKey.channel();

                    SocketChannel socketChannel = channel.accept();

                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector,
                    SelectionKey.OP_READ);

                } else if (selectionKey.isReadable()) {
                    SocketChannel channel = (SocketChannel) selectionKey.channel();

                    String request = "";
                    ByteBuffer buffer = ByteBuffer.allocate(48);

                    while (channel.read(buffer) > 0) {
                        buffer.flip();
                        while (buffer.hasRemaining()) {
                            request += (char) buffer.get();
                        }
                        buffer.clear();
                    }
                    System.out.println("Request : " + request);

                    channel.register(selector, SelectionKey.OP_WRITE, request);

                } else if (selectionKey.isWritable()) {
                    SocketChannel channel = (SocketChannel) selectionKey.channel();

                    String request = (String) selectionKey.attachment();
                    String[] split = request.split(" ");
                    String path = split[1];
                    System.out.println("Path : " + path);

                    int length = path.getBytes().length;
                    String response = "HTTP/1.1 200 OK\n" +
                                      "Date: Sat, 15 Jan 2000 14:37:12GMT\n" +
                                      "Server: ServerNio/1.0\n" +
                                      "Content-Type: text/plain\n" +
                                      "Content-Length: " + length + "\n" +
                                      "Connection: close\n\n" +
                                      path + "\n";
                    System.out.println("Response : " + response);

                    ByteBuffer buffer = ByteBuffer.wrap(response.getBytes());
                    channel.write(buffer);
                    channel.finishConnect();
                    channel.close();
                }

                keyIterator.remove();
            }
        }
    }
}
