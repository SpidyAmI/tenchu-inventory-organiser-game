package Model.CodiciEItems;

public abstract class Codice implements CodiceInterface{

    protected final String identificativo;

    @Override
    public String getCodice() {
        return this.identificativo;
    }

    public Codice(String identificativo) {this.identificativo = identificativo;}
}
