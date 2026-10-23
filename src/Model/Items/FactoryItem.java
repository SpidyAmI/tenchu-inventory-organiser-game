package Model.Items;

/**
 * Creates items. Item constructors are package-private, so this factory is
 * the only way to build them from other packages.
 */
public class FactoryItem {
    public static RareItem getRareItem(String name, boolean isUnique) {return new RareItem(name,isUnique);}
    public static CommonItem getCommonItem(String name, int amount) {return new CommonItem(name,amount);}
}
