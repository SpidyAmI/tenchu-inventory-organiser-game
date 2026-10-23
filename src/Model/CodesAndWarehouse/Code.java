package Model.CodesAndWarehouse;

/**
 * A three-letter combination granting a special action.
 */
public abstract class Code implements CodeInterface {

    protected final String identifier;

    @Override
    public String getCode() {
        return this.identifier;
    }

    public Code(String identifier) {this.identifier = identifier;}
}
