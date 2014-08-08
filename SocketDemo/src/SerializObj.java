

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
	private String byteArrayToString(byte[] data) {
		BASE64Encoder enc = new BASE64Encoder();
		return enc.encode(data);
	}

	/**
	 * string to byte array
	 * 
	 * @param str
	 * @return
	 * @author zili
	 */
	private byte[] stringToByteArray(String str) {
		BASE64Decoder dec = new BASE64Decoder();
		try {
			return dec.decodeBuffer(str);
		} catch (IOException e) {
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
	public String writeObjToString(Object obj) throws Exception {
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
			return this.byteArrayToString(data);
		} catch (IOException e) {
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
	public Object readStringToObj(String str) throws Exception {
		try {
			Object obj = new Object();
			byte[] data = this.stringToByteArray(str);
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
	public String writeObjToZipString(Object obj) throws Exception {
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
			data = this.zip(data);
			return this.byteArrayToString(data);
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
	public Object readZipStringToObj(String str) throws Exception {
		try {
			Object obj = new Object();
			byte[] data = this.stringToByteArray(str);
			data = this.unzip(data);
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
	private byte[] unzip(byte[] zipBytes) throws Exception {
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
	private byte[] zip(byte[] unzipBytes) throws Exception {
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
