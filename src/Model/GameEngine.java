package Model;

import Model.CodesAndWarehouse.*;
import Model.Exceptions.NotExistingCode;
import Model.Exceptions.NinjaException;

import java.util.ArrayList;
import java.util.List;

public class GameEngine {
    private Inventory i;
    private Warehouse w;
    private List<CodeInterface> codes;

    public GameEngine(Warehouse w){
        this.w = w;
        codes = new ArrayList<>();
        codes.add(new DDA());
        codes.add(new DDS());
        codes.add(new DWA());
        this.i = Inventory.getInstance();
    }

    /**
     * Runs the code matching the given string.
     */
    public void tryCode(String code) throws NinjaException{

        boolean find = false;

        for(int i = 0; i < codes.size(); i++){
            if (code.equals(codes.get(i).getCode())){
                codes.get(i).execute(this.i,this.w);
                find = true;
            }
        }

        if (!find) throw new NotExistingCode();
    }
}
