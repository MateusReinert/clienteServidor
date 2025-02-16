package org.example;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;

public class RoomManagerImpl extends UnicastRemoteObject implements RoomManager {
    private final Map<Integer, Integer> rooms;
    private final Map<String, Integer> reservations;

    public RoomManagerImpl() throws RemoteException {
        rooms = new HashMap<>();
        rooms.put(0, 10);
        rooms.put(1, 20);
        rooms.put(2, 5);
        rooms.put(3, 3);
        rooms.put(4, 2);
        reservations = new HashMap<>();
    }

    public synchronized String listRooms() throws RemoteException {
        return rooms.get(0) + " quartos do tipo 0 disponíveis por 55 reais por noite\n" +
                rooms.get(1) + " quartos do tipo 1 disponíveis por 75 reais por noite\n" +
                rooms.get(2) + " quartos do tipo 2 disponíveis por 80 reais por noite\n" +
                rooms.get(3) + " quartos do tipo 3 disponíveis por 150 reais por noite\n" +
                rooms.get(4) + " quartos do tipo 4 disponíveis por 230 reais por noite\n";
    }

    public synchronized String bookRoom(int roomType, String guestName) throws RemoteException {
        if (rooms.getOrDefault(roomType, 0) > 0) {
            rooms.put(roomType, rooms.get(roomType) - 1);
            reservations.put(guestName, roomType);
            return "Reserva feita para " + guestName + " no quarto tipo " + roomType;
        }
        return "Nenhum quarto do tipo " + roomType + " disponível.";
    }

    public synchronized List<String> listGuests() throws RemoteException {
        return new ArrayList<>(reservations.keySet());
    }
}
