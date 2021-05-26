package com.application;

import catalog.services.CatalogService;
import catalog.services.Menu;

public class Main {

    public static void main(String[] args) throws Exception {
//        System.out.println (DBManager.getInstance ().select ("TEST_TABLE"));
//        DBManager.getInstance ().insert ("TEST_TABLE", new String[] {"name"}, new String[] {"Rosu"} );
//        System.out.println (DBManager.getInstance ().select ("TEST_TABLE"));
//        DBManager.getInstance ().update ("TEST_TABLE", new String[] {"name"}, new String[] {"Blaga"}, "name='Rosu'");
//        System.out.println (DBManager.getInstance ().select ("TEST_TABLE"));
//        DBManager.getInstance ().delete ("TEST_TABLE", "name='Blaga'");
//        System.out.println (DBManager.getInstance ().select ("TEST_TABLE"));
        CatalogService myCatalogCatalogService = new CatalogService();
        Menu myMenu = new Menu(myCatalogCatalogService);

        myMenu.console ();
    }

}
