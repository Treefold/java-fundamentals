package polymorphism.zoo;

import polymorphism.zoo.carnivor.Leu;
import polymorphism.zoo.carnivor.Pisica;
import polymorphism.zoo.ierbivor.Cal;
import polymorphism.zoo.ierbivor.Elefant;
import polymorphism.zoo.omnivor.Caine;
import polymorphism.zoo.omnivor.Urs;

import java.util.Scanner;

public class ZooTest {

    public static void main (String[] args) {

        System.out.println ("Precizati nr max de animale care pot fi gazduite la zoo: ");
        /*
        int nrAnimaleZoo = nteger.parseInt(args[0]);
        /*/
        Scanner scanner = new Scanner(System.in);
        int nrAnimaleZoo = scanner.nextInt(); //Integer.parseInt(args[0]);
        scanner.close();//*/

        Zoo zooBucuresti = new Zoo (nrAnimaleZoo);
        adaugaAnimaleLaZoo (zooBucuresti);

        for (int i = 0; i < zooBucuresti.animaleZoo.length; ++i) {
            Animal animal = zooBucuresti.animaleZoo[i];
            if (animal != null) {
                animal.afiseazaDetalii();
                animal.seHraneste();
                animal.scoateSunet();
            }
        }

        Pisica pisica = new Pisica ("Thomas", 7);
        Pisica pisica1 = new Pisica ("Thomas", 7);
        System.out.println(pisica.equals(pisica1));
    }

    public static  void adaugaAnimaleLaZoo (Zoo zoo) {
        Leu     leu     = new Leu     ("Simba",   7);
        Elefant elefant = new Elefant ("Eli",     10);
        Urs     urs     = new Urs     ("Fram",    2);
        Pisica  pisica  = new Pisica  ("Tom",     2);
        Caine   caine   = new Caine   ("Toto",    3);
        Cal     cal     = new Cal     ("Thunder", 3);

        zoo.adaugaAimal (leu);
        zoo.adaugaAimal (elefant);
        zoo.adaugaAimal (urs);
        zoo.adaugaAimal (pisica);
        zoo.adaugaAimal (caine);
        zoo.adaugaAimal (cal);
    }
}
