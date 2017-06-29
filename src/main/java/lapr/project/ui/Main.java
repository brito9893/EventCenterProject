package lapr.project.ui;


import lapr.project.model.*;


/**
 * @author Grupo 66 21/06/17.
 */
class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        EventCenter ec = new EventCenter();


        new LoginUI(ec);
    }

}
