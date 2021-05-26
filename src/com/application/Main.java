package com.application;

<<<<<<< HEAD
import catalog.services.CatalogService;
import catalog.services.Menu;
=======
import catalog.services.Menu;
import catalog.services.CatalogService;
>>>>>>> b143c6e0b11dbe7da7c2cccf88afba388988f0cf

public class Main {

    public static void main(String[] args) throws Exception {
<<<<<<< HEAD
//        System.out.println (DBManager.getInstance ().select ("TEST_TABLE"));
//        DBManager.getInstance ().insert ("TEST_TABLE", new String[] {"name"}, new String[] {"Rosu"} );
//        System.out.println (DBManager.getInstance ().select ("TEST_TABLE"));
//        DBManager.getInstance ().update ("TEST_TABLE", new String[] {"name"}, new String[] {"Blaga"}, "name='Rosu'");
//        System.out.println (DBManager.getInstance ().select ("TEST_TABLE"));
//        DBManager.getInstance ().delete ("TEST_TABLE", "name='Blaga'");
//        System.out.println (DBManager.getInstance ().select ("TEST_TABLE"));
        CatalogService myCatalogCatalogService = new CatalogService();
        Menu myMenu = new Menu(myCatalogCatalogService);
=======
        CatalogService myCatalogCatalogService = new CatalogService();
        Menu    myMenu                         = new Menu (myCatalogCatalogService);
>>>>>>> b143c6e0b11dbe7da7c2cccf88afba388988f0cf

        myMenu.console ();
    }

}
