package trainingportal.service;

import trainingportal.model.InfoDesk;
import trainingportal.model.QuestionStatus;
import trainingportal.service.generic.GenericService;

import java.util.List;

public interface InfoDeskService extends GenericService<InfoDesk> {

    List<InfoDesk> getListOfUsersThatNeedHelp(int page, int rowsPerPage);

    List<InfoDesk> getListOfUsersThatHadAnswer(int page, int rowsPerPage);

    List<InfoDesk> getQuestionsListOfUser (Long employeeId);

    int getPages(Long infoDeskStatusId, double rowsPerPage);

    List<QuestionStatus> getStatusList();

    QuestionStatus findStatusById(Long id);

}
