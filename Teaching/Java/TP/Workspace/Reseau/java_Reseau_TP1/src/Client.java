import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
 
public class Client {
     
    public static void main(String[] args) throws IOException,
            InterruptedException {
        int port = 4444;
        SocketChannel channel = SocketChannel.open();

        channel.configureBlocking(false);
        channel.connect(new InetSocketAddress("localhost", port));
 
        while (!channel.finishConnect()) {
            // System.out.println("still connecting");
        }
        while (true) {
            ByteBuffer bufferA = ByteBuffer.allocate(20);
            //int count = 0;
            String message = "";
            while ((channel.read(bufferA)) > 0) {
                bufferA.flip();
                message += Charset.defaultCharset().decode(bufferA);
 
            }
 
            if (message.length() > 0) {
                System.out.println(message);
                CharBuffer buffer = CharBuffer.wrap("Hello Server");
                while (buffer.hasRemaining()) {
                    channel.write(Charset.defaultCharset().encode(buffer));
                }
                message = "";
            }
 
        }
    }
}