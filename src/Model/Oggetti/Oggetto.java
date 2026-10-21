package Model.Oggetti;

import Model.Eccezioni.OggettoFinito;
import Model.Eccezioni.OggettoNonSbloccato;
import javafx.scene.paint.Color;

import java.util.Objects;

public abstract class Oggetto implements OggettoInterface, Comparable<Oggetto>{
    protected final String nome;
    protected int ammontare;
    protected final int MAX_AMMONTARE;
    protected boolean isUnlocked;

    Oggetto(boolean isUnlocked, int MAX_AMMONTARE, int ammontare, String nome) {
        this.isUnlocked = isUnlocked;
        this.MAX_AMMONTARE = MAX_AMMONTARE;
        this.ammontare = ammontare;
        this.nome = nome;
    }

    @Override
    public void sblocca() {this.isUnlocked = true;}

    @Override
    public void incrementa() {
        if (this.ammontare < MAX_AMMONTARE) this.ammontare++;
        if(! this.isUnlocked) this.isUnlocked = true;
    }

    @Override
    public Color getColore() {
        if(this.isUnlocked)
            return Color.LIGHTGRAY;
        return Color.DARKGRAY;
    }

    @Override
    public int compareTo(Oggetto o) {return this.nome.compareTo(o.nome);}

    @Override
    public String getNome() {return this.nome;}

    @Override
    public String toString() {return this.nome + "\n    " + this.ammontare;}

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Oggetto oggetto = (Oggetto) o;
        return ammontare == oggetto.ammontare && MAX_AMMONTARE == oggetto.MAX_AMMONTARE && isUnlocked == oggetto.isUnlocked && Objects.equals(nome, oggetto.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, ammontare, MAX_AMMONTARE, isUnlocked);
    }

    @Override
    public void readyToEquip() throws OggettoFinito, OggettoNonSbloccato {
        if(! this.isUnlocked)
            throw new OggettoNonSbloccato();
        if(this.ammontare == 0)
            throw new OggettoFinito();
    }

    @Override
    public void decrementa() {this.ammontare--;}

    @Override
    public boolean isUnlocked() {return isUnlocked;}
}
