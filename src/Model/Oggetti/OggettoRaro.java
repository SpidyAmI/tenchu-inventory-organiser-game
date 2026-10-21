package Model.Oggetti;

public class OggettoRaro extends Oggetto{

    protected boolean isUnique;

    OggettoRaro( String nome, boolean isUnique) {
        super(false, isUnique ? 1 : 3, 0, nome);
        this.isUnique = isUnique;
    }
}
