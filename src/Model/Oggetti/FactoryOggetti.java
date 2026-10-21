package Model.Oggetti;

public class FactoryOggetti {
    public static OggettoRaro getOggettoRaro(String nome, boolean isUnique) {return new OggettoRaro(nome,isUnique);}
    public static OggettoComune getOggettoComune(String nome, int ammontare) {return new OggettoComune(nome,ammontare);}
}
