package TCPServer;

import java.net.*;

import org.apache.commons.lang.SerializationUtils;

import java.io.*;

public class UDPServer {
	public static void main(String args[]) throws ClassNotFoundException {
		DatagramSocket aSocket = null;
		try {
			aSocket = new DatagramSocket(6789);
			byte[] buffer = new byte[1000];
			while (true) {
				DatagramPacket request = new DatagramPacket(buffer, buffer.length);
				aSocket.receive(request);
				
				byte []mm = request.getData();
				byte []nn = new byte[165];
				
				for(int i = 0; i < 165; i++)
					nn[i] = mm[i];
					//System.out.println(mm[i]);
				
				for(int i = 0; i < 165; i++)
					//nn[i] = mm[i];
					System.out.println(nn[i]);
				Message ms = (Message) toObject(nn);
				//Object object = (Object) SerializationUtils.deserialize(nn);
				//Message ms = (Message)object;
				
				System.out.println(ms.getA() + "    "+ms.getB());
				
				//DatagramPacket reply = new DatagramPacket(request.getData(), request.getLength(), request.getAddress(),request.getPort());
				
				//String s = new String(request.getData());
				
				//System.out.println(s + " " + request.getAddress());
				//aSocket.send(reply);
			}
		} catch (SocketException e) {
			System.out.println("Socket: " + e.getMessage());
		} catch (IOException e) {
			System.out.println("IO: " + e.getMessage());
		} finally {
			if (aSocket != null)
				aSocket.close();
		}
	}
	
/*	private static Object convertFromBytes(byte[] bytes) throws IOException, ClassNotFoundException {
	    try (ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
	         ObjectInput in = new ObjectInputStream(bis)) {
	        return in.readObject();
	    } 
	}*/
	
	public static Object toObject(byte[] bytes) throws IOException, ClassNotFoundException {
        Object obj = null;
        ByteArrayInputStream bis = null;
        ObjectInputStream ois = null;
        try {
            bis = new ByteArrayInputStream(bytes);
            ois = new ObjectInputStream(bis);
            obj = ois.readObject();
        } finally {
            if (bis != null) {
                bis.close();
            }
            if (ois != null) {
                ois.close();
            }
        }
        return obj;
    }
}
