import java.util.Scanner;

public class VerkkokauppaUI {
    private Verkkokauppa verkkokauppa;
    private Scanner lukija;

    public static void main(String[] args) {
        VerkkokauppaUI ui = new VerkkokauppaUI();
        ui.aloita();
    }

    public VerkkokauppaUI() {
        verkkokauppa = new Verkkokauppa();
        lukija = new Scanner(System.in);
    }

    public void aloita() {
        int valinta = -1; // joku muu kuin 0
        while (valinta != 0) {
            tulostaMenu();
            valinta = lueValinta(0, 4);
            if (valinta == 1) {
                asiakasMenu();
            } else if (valinta == 2) {
                tuoteMenu();
            } else if (valinta == 3) {
                myyjaMenu();
            } else if (valinta == 4) {
                ostotapahtumaMenu(); // ei vielä toteutettu...
            }
        }
    }



    private void asiakasMenu() {
        int valinta = -1;
        while (valinta != 0) {
            System.out.println(verkkokauppa.listaaAsiakkaat());
            System.out.println();
            System.out.println("1. Lisää asiakas");
            System.out.println("2. Poista asiakas");
            System.out.println("3. Muuta kanta-asiakkaaksi");
            System.out.println("0. Poistu");

            valinta = lueValinta(0, 3);
            if (valinta == 1) {
                String asnro = lueMerkkijono("Asiakasnumero");
                String nimi = lueMerkkijono("Nimi");
                verkkokauppa.lisaaAsiakas(new Asiakas(asnro, nimi, 0));
                System.out.println("Asiakas lisätty!");
            } else if (valinta == 2) {
                String asnro = lueMerkkijono("Asiakasnumero");
                Asiakas as = verkkokauppa.annaAsiakas(asnro);
                if (as != null) {
                    verkkokauppa.poistaAsiakas(as);
                    System.out.println("Asiakas poistettu!");
                } else {
                    System.out.println("Asiakasta ei löytynyt.");
                }
            } else if (valinta == 3) {
                String asnro = lueMerkkijono("Asiakasnumero");
                Asiakas as = verkkokauppa.annaAsiakas(asnro);
                if (as != null) {
                    KantaAsiakas ka = new KantaAsiakas(as.getAsiakasNumero(),
                            as.getNimi(), as.getOstojaTehty(), 0);
                    verkkokauppa.poistaAsiakas(as);
                    verkkokauppa.lisaaAsiakas(ka);
                    System.out.println("Muutettu!");
                } else {
                    System.out.println("Asiakasta ei löytynyt.");
                }
            }
        }
    }

    private void tuoteMenu() {
    }

    private void myyjaMenu() {

    }

    private void ostotapahtumaMenu() {

    }

    private void tulostaMenu() {
        System.out.println("1. Asiakkaat");
        System.out.println("2. Tuotteet");
        System.out.println("3. Myyjät");
        System.out.println("4. Ostotapahtumat");
        System.out.println("0. Poistu");
    }


    /**
     * Lukee käyttäjältä syötteen ja palauttaa sen kokonaislukuna.
     * Kokonaisluvun tulee olla annetun minimi ja maksimiarvon välissä.
     *
     * @param minimi  pienin sallittu arvo
     * @param maksimi suurin sallittu arvo
     * @return käyttäjän syötteen kokonaislukuna
     */
    private int lueValinta(int minimi, int maksimi) {
        while (true) {
            System.out.print("Valitse toiminto: ");
            try {
                int valinta = Integer.parseInt(lukija.nextLine());
                if (valinta >= minimi && valinta <= maksimi) {
                    return valinta;
                }
                System.out.println("Valinnan pitää olla väliltä " +
                        minimi + " - " + maksimi);
            } catch (NumberFormatException nfe) {
                System.out.println("Anna valinta numerona!");
            }
        }
    }

    /**
     * Apumetodi, joka näyttää käyttäjälle annetun kehotteen ja lukee
     * sitten tältä merkkijonon. Annettu jono ei voi olla tyhjä.
     *
     * @param kehote käyttäjälle näytettävä kehote
     * @return käyttäjän syöttämän ei-tyhjän merkkijonon
     */
    private String lueMerkkijono(String kehote) {
        while (true) {
            System.out.print(kehote + ": ");
            String arvo = lukija.nextLine();
            if (!arvo.equals("")) {
                return arvo;
            }
        }
    }

}
