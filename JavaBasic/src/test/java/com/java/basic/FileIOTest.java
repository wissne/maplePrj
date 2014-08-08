package com.java.basic;

import org.junit.Test;

import java.io.*;

/**
 * Created by maple on 2014/6/2.
 */
public class FileIOTest {

    @Test
    public void getFileName() {
        File file = new File("hello.txt");
        System.out.println(file.getName());
        System.out.println(file.getPath());
        System.out.println(file.getAbsoluteFile());
        System.out.println(file.getAbsolutePath());
    }

    @Test
    public void readFile() throws IOException {
        File file = new File("src/main/resources/hello.txt");
        System.out.println(file.getAbsolutePath());
        InputStream fis = new FileInputStream(file);
        byte[] buffer = new byte[fis.available()];

        int len = 0;
        while ((len = fis.read(buffer)) != -1) {
            String str = new String(buffer);
            System.out.println(str);
            buffer = new byte[5];
        }
        fis.close();
    }

    @Test
    public void testReader() throws IOException {
        Reader reader = new FileReader("src/main/resources/hello.txt");

        char[] buffer = new char[10];

        int len = 0;
        while ((len = reader.read(buffer)) != -1) {
            System.out.println(buffer);
            buffer = new char[4];
        }
    }

    @Test
    public void testOutputStream() throws IOException {
        OutputStream os = new FileOutputStream("src/main/resources/abcd.txt");
        String content = "www.atguigu.com\nHello Java";

        // Method 1
//        byte[] buffer = content.getBytes();
//        for (int i = 0; i < (buffer.length / 10) + (buffer.length % 10 !=0 ? 1: 0); i++) {
//            os.write(buffer, i * 10, (i < buffer.length/ 10 ? 10 : (buffer.length - i * 10)));
//        }
        // Method 2
//        os.write(content.getBytes());
        os.close();
    }

    @Test
    public void testCopyByReaderAndWriter() throws IOException {
        Reader reader = new FileReader("src/main/resources/abcd.txt");
        Writer writer = new FileWriter("src/main/resources/abcd2.txt");
        char[] buffer = new char[4];

        int len = 0;
        while ((len = reader.read(buffer)) != -1) {
            writer.write(buffer, 0, len);
        }
        reader.close();
        writer.close();
    }

    @Test
    public void testBufferedReaderAndWriter() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/abcd.txt"));
        BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/resources/abcd3.txt"));

        String str;
        int i = 0;
        while ((str = reader.readLine()) != null) {
            if (i != 0)
                writer.newLine();
            writer.write(str);
            i++;
        }
        reader.close();
        writer.close();
    }

    @Test
    public void testInputStreamReader() throws  IOException {
        InputStream inputStream = new FileInputStream("src/main/resources/abcd.txt");
        Reader reader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(reader);

        String str = null;
        while((str = bufferedReader.readLine()) != null) {
            System.out.println(str);
        }

        inputStream.close();
        reader.close();
        bufferedReader.close();

    }

    @Test
    public void tesObjectOutputStream() throws IOException {
        Child child = new Child();
        child.setName("maple");
        child.setAge(123);

        OutputStream outputStream = new FileOutputStream("src/main/resources/child.txt");
        ObjectOutputStream out = new ObjectOutputStream(outputStream);

        out.writeObject(child);
        out.close();
    }

    @Test
    public void testObjectInputStream() throws IOException, ClassNotFoundException {
        Child child = new Child();

        ObjectInputStream input = new ObjectInputStream(new FileInputStream("src/main/resources/child.txt"));

        child = (Child) input.readObject();
        input.close();
        System.out.println(child);
    }

    @Test
    public void testRandomAccessFile() throws IOException {
        RandomAccessFile access = new RandomAccessFile("src/main/resources/abcd.txt","rw");
        String str;
        while ((str = access.readLine()) != null) {
            System.out.println(str);
        }
        access.seek(0);
        access.writeUTF("New line by random access file\n"); // Will overwrite the original text
        access.close();
    }

    @Test
    public void testRandomAccessFileInsert() throws IOException {
        RandomAccessFile access = new RandomAccessFile("src/main/resources/abcd.txt","rw");
        String line = access.readLine();

        byte[] buff = new byte[(int) (access.length() - line.length())];
        access.read(buff);
        access.seek(line.length());
        access.writeUTF("\nNew line by random access file\n");
        access.write(buff);
        access.close();
    }

}
