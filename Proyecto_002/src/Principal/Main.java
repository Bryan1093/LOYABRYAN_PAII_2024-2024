package Principal;

import Figuras.Figuras;
import Figuras.Triangulo;

import java.util.Scanner;

import Figuras.Circulo;
import Figuras.Cuadrado;

public class Main {
    public static void main(String[] args) {

    	Scanner scanner = new Scanner(System.in);
        boolean continuar = true;

        while (continuar) {
            System.out.println("Seleccione la figura que desea dibujar:");
            System.out.println("1. Circulo");
            System.out.println("2. Cuadrado");
            System.out.println("3. Triangulo");
            System.out.println("0. Salir");

            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                	Figuras figC = new Figuras(new Circulo());
                    break;
                case 2:
                	Figuras figCu = new Figuras(new Cuadrado());
                    break;
                case 3:
                	Figuras figTr = new Figuras(new Triangulo());
                    break;
                case 0:
                    continuar = false;
                    break;
                default:
                    System.out.println("Opción no válida");
                    break;
            }
        }

        scanner.close();
    	System.out.println("Hasta pronto!!");

    }
}