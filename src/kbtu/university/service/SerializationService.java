package kbtu.university.service;

import java.io.*;

public class SerializationService {

    public static void save(Object obj, String fileName) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName));
            oos.writeObject(obj);
            oos.close();
        } catch (Exception e) {
            System.out.println("Save error");
        }
    }

    public static Object load(String fileName) {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName));
            Object obj = ois.readObject();
            ois.close();
            return obj;
        } catch (Exception e) {
            System.out.println("Load error");
            return null;
        }
    }
}