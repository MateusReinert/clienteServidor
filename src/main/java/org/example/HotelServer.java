package org.example;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class HotelServer {
    public static void main(String[] args) {
        try {
            RoomManager manager = new RoomManagerImpl();
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("RoomManager", manager);
            System.out.println("Servidor pronto.");
        } catch (Exception e) {
            System.out.println("Falha ao conectar");
        }
    }
}
