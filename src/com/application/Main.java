package com.application;

import catalog.services.Menu;
import catalog.services.CatalogService;

public class Main {

    public static void main(String[] args) throws Exception {
        CatalogService myCatalogCatalogService = new CatalogService();
        Menu    myMenu                         = new Menu (myCatalogCatalogService);

        myMenu.console ();
    }

}
