/*
 * @fileoverview    {TareaProgramada} se encarga de realizar tareas específicas.
 *
 * @version         2.0
 *
 * @author          Dyson Arley Parra Tilano <dysontilano@gmail.com>
 *
 * @copyright       Dyson Parra
 * @see             github.com/DysonParra
 *
 * History
 * @version 1.0     Implementación realizada.
 * @version 2.0     Documentación agregada.
 */
package com.project.dev.tester;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.dev.dummy.printer.SocketPrinter;
import com.project.dev.dummy.printer.objecttoprint.autopistasdelcafe.ticket.SocketTicket;
import java.util.List;
import java.util.Scanner;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * TODO: Definición de {@code TareaProgramada}.
 *
 * @author Dyson Parra
 * @since 1.8
 */
@Component
public class TareaProgramada {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * TODO: Definición de {@code startProgramProcess}.
     *
     */
    @Scheduled(fixedRate = 86400000, initialDelay = 2000)
    public void startProgramProcess() {
        System.out.println("Start program...");

        String json = "{\n"
                + "    \"consecutivo\": \"10032\",\n"
                + "    \"operador\": \"operador1\",\n"
                + "    \"sentidoVia\": \"sentidoVia1\",\n"
                + "    \"fechaRecuperacion\": \"07/23/2021 21:27:55\",\n"
                + "    \"pesoRegistrado\": \"pesoRegistrado1\",\n"
                + "    \"pesoMaximo\": \"pesoMaximo1\",\n"
                + "    \"sobrePeso\": \"sobrePeso1\",\n"
                + "    \"categoria\": \"categoria\",\n"
                + "    \"placa\": \"placa1\"\n"
                + "}";
        SocketTicket socketTicket = null;
        try {
            socketTicket = objectMapper.readValue(json, SocketTicket.class);
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        System.out.println(socketTicket);
        List<String> list;
        if (socketTicket != null)
            list = socketTicket.makeListToPrint();
        //for (String line : list)
        //    System.out.println(line);

        System.out.println("Printer started...");
        //ImgPrinter imgPrinter = new ImgPrinter();
        //TxtPrinter txtPrinter = new TxtPrinter();
        //ImgTicket imgTicket = new ImgTicket();
        //TxtTicket txtTicket = new TxtTicket();
        SocketPrinter socketPrinter = new SocketPrinter();

        //imgPrinter.printObject(imgTicket, "PDF");
        //txtPrinter.printObject("PDF", txtTicket);
        //txtPrinter.printObject("Bioxin", txtTicket);
        //if (!socketPrinter.isConnected())
        //    System.out.println("Printer disconnected");
        String reply;
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\nWrite:");
            System.out.println("1: print");
            System.out.println("0: exit");
            reply = sc.nextLine();
            if (reply.matches("1")) {
                try {
                    socketPrinter.printObject("localhost", 5200, socketTicket);
                } catch (Exception ex) {
                    System.out.println("Error printing: " + socketTicket);
                }
            } else if (reply.matches("0"))
                break;
            else
                System.out.println("Write a valid option");
        }
        System.exit(0);
    }

}
