package lapr.project.controller;

import lapr.project.model.*;
import lapr.project.model.registers.*;

import java.util.*;

/**
 * Assign Stand To Application Controller
 */
public class AssignStandToApplicationController {

    private StandList standRegister;
    private GetEventsAndApplicationsController getEventsAndApplicationsController;

    public AssignStandToApplicationController(EventCenter eventCenter) {
        getEventsAndApplicationsController = new GetEventsAndApplicationsController(eventCenter);
    }

    public GetEventsAndApplicationsController getEventsAndApplicationsController() {
        return getEventsAndApplicationsController;
    }

    public List<String> getStands() {
        standRegister = getEventsAndApplicationsController.getEvent().getStandRegister();
        List<String> standsNameList = standRegister.getStandsNameList();
        if (!"No stands to show".equals(standsNameList.get(0))) {
            for (String standString : standRegister.getStandsNameList()) {
                Stand stand = standRegister.getStand(Integer.parseInt(standString));
                List<Application> applicationList = getEventsAndApplicationsController.getApplicationRegister().getApplicationList();
                for (Application application : applicationList) {
                    if (stand.equals(application.getStand())) {
                        standsNameList.remove(standString);
                    }
                }
            }
        }
        if (standsNameList.isEmpty()) {
            standsNameList.add("No stands to show");
        }
        return standsNameList;
    }

    /**
     * Assigns stand to application
     *
     * @param standID       : Stand
     * @param applicationID : Application
     */
    public void assignStandToApplication(String standID, String applicationID) {
        Application application = getEventsAndApplicationsController.getApplicationRegister().getApplication(applicationID);
        Stand stand = standRegister.getStand(Integer.parseInt(standID));

        if (application.getStand() == null) {
            application.setStand(stand);
        }
    }
}
