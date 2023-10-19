package org.example.ConsoleSpeaker;

import org.example.Entity.Order;
import org.example.Entity.Repairer;
import org.example.Storages.OrderStorageEnum;
import org.example.Storages.RepairerStorageEnum;

import java.util.Scanner;

public class ConsoleSpeaker {

    public void speakWithConsole(){
        var repairerStorageSingletone = RepairerStorageEnum.REPAIRER_STORAGE;
        var orderStorageSingletone = OrderStorageEnum.ORDER_STORAGE;
        String hints = """
                Введите 'q' чтобы выйти из программы
                Введите 'h' чтобы увидеть подсказки
                Примеры команд:
                repairer hire Andrew
                repairer fire 7
                repairer showAllRepairers
                -----------------------
                order create
                order assignRepairer orderID repairerID
                order completeOrder orderID
                order cancelOrder orderID
                order showAllOrders
                order showOrder orderID
                
                Введите нужную команду:
                """;
        System.out.println(hints);

        Scanner sc1 = new Scanner(System.in);
        while (sc1.hasNext()){
            String command = sc1.nextLine();
            if(command.equals("q")) break;
            if(command.equals("h")) System.out.println(hints);
            String[] splittedCommand = command.split(" ");

            if(splittedCommand[0].equals("repairer")){
                if(splittedCommand[1].equals("hire")) {
                    Repairer repairer = Repairer.repairerCreator(splittedCommand[2]);
                    repairerStorageSingletone.save(repairer);
                }
                if(splittedCommand[1].equals("fire")) {
                    repairerStorageSingletone.remove(Long.valueOf(splittedCommand[2]) );
                }
                if(splittedCommand[1].equals("showAllRepairers")) {
                    System.out.println(repairerStorageSingletone.findAll());
                }
            } else if(splittedCommand[0].equals("order")){
                if(splittedCommand[1].equals("create")) {
                    Order order = new Order();
                    orderStorageSingletone.save(order);
                }
                if(splittedCommand[1].equals("assignRepairer")) {
                    Order order = orderStorageSingletone.getOrders().get(Long.valueOf(splittedCommand[2]));
                    order.assignRepairers(Long.valueOf(splittedCommand[3]));
                }
                if(splittedCommand[1].equals("completeOrder")) {
                    Order order = orderStorageSingletone.getOrders().get(Long.valueOf(splittedCommand[2]));
                    order.completeOrder();
                }
                if(splittedCommand[1].equals("cancelOrder")) {
                    Order order = orderStorageSingletone.getOrders().get(Long.valueOf(splittedCommand[2]));
                    order.cancelOrder();
                }
                if(splittedCommand[1].equals("showAllOrders")) {
                    orderStorageSingletone.findAll().forEach(System.out::println);
                }
                if(splittedCommand[1].equals("showOrder")) {
                    Order order = orderStorageSingletone.getOrders().get(Long.valueOf(splittedCommand[2]));
                    System.out.println(order.information());
                }

            }
        }
        System.out.println("THE END ¯\\_(ツ)_/¯");
    }

}
