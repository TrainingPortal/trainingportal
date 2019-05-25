package trainingportal.dao;

import trainingportal.dao.generic.GenericDao;
import trainingportal.model.InfoDesk;

import java.util.List;

public interface InfoDeskDao extends GenericDao<InfoDesk> {

    List<InfoDesk> getAllAsPage(int page, int total);
    int countAll();
    List<InfoDesk> getRequestForHelpByEmpId(Long employeeId);


}
