package org.example;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class HotelClient {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Uso: java HotelClient <comando> <endereço> [parâmetros]");
            return;
        }

        String command = args[0];
        String serverAddress = args[1];

        try {
            Registry registry = LocateRegistry.getRegistry(serverAddress, 1099);
            RoomManager manager = (RoomManager) registry.lookup("RoomManager");

            switch (command) {
                case "list":
                    System.out.println(manager.listRooms());
                    break;
                case "book":
                    if (args.length < 4) {
                        System.out.println("Uso: java HotelClient book <endereço> <tipo> <nome>");
                    } else {
                        int roomType = Integer.parseInt(args[2]);
                        String guestName = args[3];
                        System.out.println(manager.bookRoom(roomType, guestName));
                    }
                    break;
                case "clientes":
                    System.out.println("Hóspedes registrados: " + manager.listGuests());
                    break;
                default:
                    System.out.println("Comando desconhecido.");
            }
        } catch (Exception e) {
            System.out.println("Falha ao subir o cliente");
        }
    }
}
