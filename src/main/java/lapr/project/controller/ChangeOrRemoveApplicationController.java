package lapr.project.controller;

import lapr.project.model.*;
import lapr.project.model.registers.*;

/**
 * Change Or Remove Controller
 */
public class ChangeOrRemoveApplicationController {

    private KeywordList keywordList;
    private String company;
    private String description;
    private int invites;
    private int area;
    private GetEventsAndApplicationsController getEventsAndApplicationsController;

    public ChangeOrRemoveApplicationController(EventCenter eventCenter) {
        getEventsAndApplicationsController = new GetEventsAndApplicationsController(eventCenter);
    }

    public GetEventsAndApplicationsController getEventsAndApplicationsController() {
        return getEventsAndApplicationsController;
    }

    public boolean createApplication(String company, String description, int invites, int area, String keywords) {
        keywordList = new KeywordList();

        String[] keywordListArray = keywords.split(";");

        for (String keyword : keywordListArray) {
            keywordList.addKeyword(new Keyword(keyword.trim()));
        }

        this.company = company;
        this.description = description;
        this.invites = invites;
        this.area = area;

        return keywordListArray.length < 2 || keywordListArray.length > 5;
    }

    public void changeApplication() {
        Application application = getEventsAndApplicationsController.getApplication();
        application.setCompanyName(company);
        application.setDescription(description);
        application.setInvites(invites);
        application.setKeywordList(keywordList);
        application.setStandArea(area);
    }

    public void removeApplication() {
        Application application = getEventsAndApplicationsController.getApplication();
        getEventsAndApplicationsController.getApplicationRegister().removeApplication(application);
    }
}

