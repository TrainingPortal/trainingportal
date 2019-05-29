package trainingportal.dao;

import trainingportal.dao.generic.GenericDao;
import trainingportal.model.InfoDesk;
import trainingportal.model.QuestionStatus;

import java.util.List;

public interface InfoDeskDao extends GenericDao<InfoDesk> {

    List<InfoDesk> getListOfUsersThatNeedHelp(int page, int rowsPerPage);

    List<InfoDesk> getListOfUsersThatHadAnswer( int page, int rowsPerPage);

    List<InfoDesk> getQuestionsListOfUser (Long employeeId);

    int countAllByStatusId(Long infoDeskStatusId);

    List<QuestionStatus> getStatusList();

    QuestionStatus findStatusById(Long id);
}
