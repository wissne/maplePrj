package MultiUDP;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class SerializObj {

	private SerializObj() {

	}

	/**
	 * @return
	 * @author zili
	 */
	public static SerializObj getInstance() {
		return new SerializObj();
	}

	/**
	 * byte array to string
	 * 
	 * @param data
	 * @return
	 * @author zili
	 */
	private static String byteArrayToString(byte[] data) {
//		BASE64Encoder enc = new BASE64Encoder();
//		enc.encode(data);
		return new String(data);
	}

	/**
	 * string to byte array
	 * 
	 * @param str
	 * @return
	 * @author zili
	 */
	private static byte[] stringToByteArray(String str) {
//		BASE64Decoder dec = new BASE64Decoder();
		try {
//			 dec.decodeBuffer(str);
			 return str.getBytes();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Serializ the object to string
	 * 
	 * @param obj
	 *            The object must implements this
	 *            interface{java.io.Serializable}
	 * @return
	 * @throws Exception
	 * @author zili
	 */
	public static String writeObjToString(Object obj) throws Exception {
		try {
			byte[] data = new byte[100];
			ObjectOutputStream oos;
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			oos = new ObjectOutputStream(baos);
			oos.writeObject(obj);
			oos.flush();
			oos.close();
			oos = null;
			data = new byte[baos.size()];
			data = baos.toByteArray();
			return byteArrayToString(data);
		} catch (IOException e) {
			throw new Exception(e);
		}
	}
	
	public static byte[] writeObjToByte(Object obj) throws Exception {
		try {
			byte[] data = new byte[100];
			ObjectOutputStream oos;
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			oos = new ObjectOutputStream(baos);
			oos.writeObject(obj);
			oos.flush();
			oos.close();
			oos = null;
			data = new byte[baos.size()];
			data = baos.toByteArray();
			return data;
		} catch (IOException e) {
			throw new Exception(e);
		}
	}

	public static Object readByteToObj(byte[] data) throws Exception {
		try {
			Object obj = new Object();
			ObjectInputStream ois;
			ByteArrayInputStream bais = new ByteArrayInputStream(data);
			ois = new ObjectInputStream(bais);
			obj = ois.readObject();
			ois.close();
			ois = null;
			return obj;
		} catch (IOException e) {
			throw new Exception(e);
		} catch (ClassNotFoundException e) {
			throw new Exception(e);
		}
	}
	/**
	 * UnSerializ the string to object
	 * 
	 * @param str
	 * @return
	 * @throws Exception
	 * @author zili
	 */
	public static Object readStringToObj(String str) throws Exception {
		try {
			Object obj = new Object();
			byte[] data = stringToByteArray(str);
			ObjectInputStream ois;
			ByteArrayInputStream bais = new ByteArrayInputStream(data);
			ois = new ObjectInputStream(bais);
			obj = ois.readObject();
			ois.close();
			ois = null;
			return obj;
		} catch (IOException e) {
			throw new Exception(e);
		} catch (ClassNotFoundException e) {
			throw new Exception(e);
		}
	}

	/**
	 * Serializ the object to zip string
	 * 
	 * @param obj
	 *            The object must implements this
	 *            interface{java.io.Serializable}
	 * @return
	 * @throws Exception
	 * @author zili
	 */
	public static String writeObjToZipString(Object obj) throws Exception {
		try {
			byte[] data = new byte[100];
			ObjectOutputStream oos;
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			oos = new ObjectOutputStream(baos);
			oos.writeObject(obj);
			oos.flush();
			oos.close();
			oos = null;
			data = new byte[baos.size()];
			data = baos.toByteArray();
			data = zip(data);
			return byteArrayToString(data);
		} catch (IOException e) {
			throw new Exception(e);
		}
	}

	/**
	 * UnSerializ the zip string to object
	 * 
	 * @param str
	 * @return
	 * @throws Exception
	 * @author zili
	 */
	public static Object readZipStringToObj(String str) throws Exception {
		try {
			Object obj = new Object();
			byte[] data = stringToByteArray(str);
			data = unzip(data);
			ObjectInputStream ois;
			ByteArrayInputStream bais = new ByteArrayInputStream(data);
			ois = new ObjectInputStream(bais);
			obj = ois.readObject();
			ois.close();
			ois = null;
			return obj;
		} catch (IOException e) {
			throw new Exception(e);
		} catch (ClassNotFoundException e) {
			throw new Exception(e);
		}
	}

	/**
	 * UnZip byte data
	 * 
	 * @param zipBytes
	 * @return
	 * @throws Exception
	 * @author zili
	 */
	private static byte[] unzip(byte[] zipBytes) throws Exception {
		try {
			ByteArrayInputStream bais = new ByteArrayInputStream(zipBytes);
			ZipInputStream zis = new ZipInputStream(bais);
			zis.getNextEntry();
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			final int BUFSIZ = 1024;
			byte inbuf[] = new byte[BUFSIZ];
			int n;
			while ((n = zis.read(inbuf, 0, BUFSIZ)) != -1) {
				baos.write(inbuf, 0, n);
			}
			byte[] unzipBytes = baos.toByteArray();
			zis.close();
			return unzipBytes;
		} catch (IOException e) {
			throw new Exception(e);
		}
	}

	/**
	 * Zip byte data
	 * 
	 * @param unzipBytes
	 * @return
	 * @throws Exception
	 * @author zili
	 */
	private static byte[] zip(byte[] unzipBytes) throws Exception {
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ZipEntry ze = new ZipEntry("0");
			ZipOutputStream zos = new ZipOutputStream(baos);
			zos.putNextEntry(ze);
			zos.write(unzipBytes, 0, unzipBytes.length);
			zos.close();
			byte[] zipBytes = baos.toByteArray();
			return zipBytes;
		} catch (IOException e) {
			throw new Exception(e);
		}
	}
}
