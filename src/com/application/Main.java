package com.application;

import catalog.services.CatalogService;
import catalog.services.Menu;

public class Main {

    public static void main(String[] args) throws Exception {
        CatalogService myCatalogCatalogService = new CatalogService();
        Menu myMenu = new Menu(myCatalogCatalogService);

        myMenu.console ();
    }
}
