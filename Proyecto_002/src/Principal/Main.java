package Principal;

import Figuras.Figuras;
import Figuras.Triangle;

import java.util.Scanner;

import Figuras.Cercle;
import Figuras.Quadrat;

public class Main {
    public static void main(String[] args) {

    	Scanner scanner = new Scanner(System.in);
        boolean continuar = true;

        while (continuar) {
            System.out.println("Seleccione la figura que desea dibujar:");
            System.out.println("1. Cercle");
            System.out.println("2. Quadrat");
            System.out.println("3. Triangle");
            System.out.println("0. Sortir");

            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                	Figuras figC = new Figuras(new Cercle());
                    break;
                case 2:
                	Figuras figCu = new Figuras(new Quadrat());
                    break;
                case 3:
                	Figuras figTr = new Figuras(new Triangle());
                    break;
                case 0:
                    continuar = false;
                    break;
                default:
                    System.out.println("Opció no vàlida");
                    break;
            }
        }

        scanner.close();
    	System.out.println("Fins aviat!!");

    }
}