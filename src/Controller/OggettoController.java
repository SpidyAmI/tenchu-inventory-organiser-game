package Controller;

import Model.Oggetti.OggettoInterface;
import View.OggettoView;

public class OggettoController {

    private OggettoInterface o;
    private OggettoView ov;
    private InventarioController ic;

    public OggettoController(OggettoInterface o, InventarioController ic){
        this.o = o;
        this.ic = ic;
        this.ov = new OggettoView(o.getColore(),o.toString(), this);
    }

    public void refresh(){this.ov.refresh(o.getColore(),o.toString());}

    public void click(){
        this.ic.handle(this.o);
    }
    public OggettoInterface getOi(){return this.o;}

    public OggettoView getOv(){return this.ov;}
}
