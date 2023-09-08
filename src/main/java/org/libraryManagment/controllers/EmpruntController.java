package org.libraryManagment.controllers;

import org.libraryManagment.assets.Colors;
import org.libraryManagment.models.Emprunt;

import java.util.Scanner;

public class EmpruntController {

    private Scanner scanner = new Scanner(System.in);

    public void index() {}

    public void EmpruntsList() {}

    public int addEmprunt() {
        int id = 0;
        try {
            Emprunt emprunt = new Emprunt();
            System.out.printf("# > Enter Name: ");
            emprunt.setName(this.scanner.nextLine());
            System.out.printf("# > Enter CIN: ");
            emprunt.setCin(this.scanner.nextLine());
            System.out.printf("# > Enter Address: ");
            emprunt.setAddress(this.scanner.nextLine());
            if (!emprunt.show(emprunt.getCin())) {
                System.out.printf(Colors.GREEN + "---------------------------------------------%n");
                System.out.printf("             %13s          %n", emprunt.store());
                System.out.printf("---------------------------------------------%n" + Colors.RESET_COLOR);
            }
            id = emprunt.getId();
            emprunt.closeConnection();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return id;
    }

    public void showEmprunt() {}

    public void updateEmprunt() {}

    public void deleteEmprunt() {}
}
