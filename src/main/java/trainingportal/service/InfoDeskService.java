package trainingportal.service;

import trainingportal.model.InfoDesk;
import trainingportal.service.generic.GenericService;

import java.util.List;

public interface InfoDeskService extends GenericService<InfoDesk> {

    List<InfoDesk> getAllAsPage(int page, int rowsPerPage);
    List<InfoDesk> getRequestForHelpByEmpId(Long employeeId);
    int getPages(double rowsPerPage);
}
