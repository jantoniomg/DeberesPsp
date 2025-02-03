package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLOutput;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String comando = "";
        int opcion = 1;
        while (opcion != 5) {
            System.out.println("""
                    Elige una opcion:
                    1)Ping a google
                    2)Ver ipconfig
                    3)Mostrar la tabla de enrutamiento
                    4)Mostrar conexiones activas
                    5)Salir
                    """);
            Scanner sc = new Scanner(System.in);
            System.out.println("Elige una opcion");
            opcion = sc.nextInt();
            if (opcion >= 1 && opcion <= 5) {
                switch (opcion) {
                    case 1 -> {
                        comando = "ping google.com";
                    }
                    case 2 -> {
                        comando = "ipconfig";
                    }
                    case 3 -> {
                        comando = "route print";
                    }
                    case 4 -> {
                        comando = "netstat -an";
                    }
                    case 5 -> {
                        System.out.println("Saliendo del programa");
                        return;
                    }
                }
            } else {
                System.out.println("opcion incorrecta");
            }

            try {
                String os = System.getProperty("os.name").toLowerCase();
                ProcessBuilder p;
                ProcessBuilder p2;
                String archivoSalida = "salida_comando.txt";
                if (os.contains("win")) {
                    p = new ProcessBuilder("cmd.exe", "/c", "start", "cmd.exe", "/K", comando + " >> " + archivoSalida);
                    p2 = new ProcessBuilder("cmd.exe", "/c", "start", "cmd.exe", "/K", comando);
                } else {
                    p = new ProcessBuilder("gnome-terminal", "--", "bash", "-c", comando + " >> " + archivoSalida);
                    p2 = new ProcessBuilder("gnome-terminal", "--", "bash", "-c", comando);
                }
                p.start();
                p2.start();
            } catch (IOException e) {
                System.err.println("Ocurrió un error al abrir la terminal: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
}
