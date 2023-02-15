import java.util.ArrayList;

public class Verkkokauppa {
    private ArrayList<Asiakas> asiakkaat;
    private ArrayList<Tuote> tuotteet;
    private ArrayList<Myyja> myyjat;
    private ArrayList<Ostotapahtuma> tapahtumat;

    public Verkkokauppa() {
        asiakkaat = new ArrayList<>();
        tuotteet = new ArrayList<>();
        myyjat = new ArrayList<>();
        tapahtumat = new ArrayList<>();
    }

    public void lisaaAsiakas(Asiakas asiakas) {
        asiakkaat.add(asiakas);
    }

    public void lisaaTuote(Tuote tuote) {
        tuotteet.add(tuote);
    }

    public void lisaaMyyja(Myyja myyja) {
        myyjat.add(myyja);
    }

    public void lisaaTapahtuma(Ostotapahtuma tapahtuma) {
        tapahtumat.add(tapahtuma);
    }

    /**
     * Yrittää poistaa annetun asiakkaan asiakalistasta.
     *
     * @param asiakas asiakas, jonka poistoa yritetään
     * @return true, jos asiakas löytyi ja poistettiin, muuten false
     */
    public boolean poistaAsiakas(Asiakas asiakas) {
        if (asiakkaat.contains(asiakas)) {
            asiakkaat.remove(asiakas);
            return true;
        }
        return false;
    }

    /**
     * Palauttaa annetun asiakasnumeron mukaisen asiakkaan tai
     * <code>null</code>, jos asiakasta ei löydy
     *
     * @param asiakasnumero halutun asiakkaan asiakasnumero
     * @return pyydetyn asiakkaan tai null, jos asiakasta ei löydy
     */
    public Asiakas annaAsiakas(String asiakasnumero) {
        for (Asiakas asiakas : asiakkaat) {
            if (asiakas.getAsiakasNumero().equals(asiakasnumero)) {
                return asiakas;
            }
        }
        return null;
    }

    /**
     * Palauttaa kaikki asiakkaat yhtenä merkkijonona.
     *
     * @return asiakalistan merkkijonona
     */
    public String listaaAsiakkaat() {
        // Tekee otsikkorivin
        // \t on tabulaattori, joka tasaa tulosteen
        // sarakkeisiin; \n tekee rivinvaihdon
        StringBuilder s = new StringBuilder("Asiakasnumero\tNimi\t\t\tOstoja\tKanta-asiakas\n");
        for (Asiakas asiakas : asiakkaat) {
            s.append(asiakas.getAsiakasNumero() + "\t\t\t");
            s.append(asiakas.getNimi() + "\t");
            s.append(asiakas.getOstojaTehty() + "\t\t");
            if (asiakas instanceof  KantaAsiakas) {
                s.append("On\n");
            } else {
                s.append("Ei\n");
            }
        }
        return s.toString();
    }
}
