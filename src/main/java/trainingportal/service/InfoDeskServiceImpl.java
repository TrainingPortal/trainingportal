package trainingportal.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import trainingportal.dao.InfoDeskDao;
import trainingportal.model.InfoDesk;
import trainingportal.model.QuestionStatus;

import java.util.List;

@Service("infoDeskServiceImpl")
@Transactional
public class InfoDeskServiceImpl implements InfoDeskService {

    @Autowired
    InfoDeskDao infoDeskDao;

    @Override
    public List<InfoDesk> getListOfUsersThatNeedHelp( int page, int rowsPerPage) {
        page = getPageNumber(page,rowsPerPage);
        return infoDeskDao.getListOfUsersThatNeedHelp(page, rowsPerPage);
    }

    @Override
    public List<InfoDesk> getListOfUsersThatHadAnswer(int page, int rowsPerPage) {
        page = getPageNumber(page,rowsPerPage);
        return infoDeskDao.getListOfUsersThatHadAnswer(page,rowsPerPage);
    }

    @Override
    public List<InfoDesk> getQuestionsListOfUser(Long employeeId) {
        return infoDeskDao.getQuestionsListOfUser( employeeId);
    }

    @Override
    public int getPages(Long infoDeskStatusId, double rowsPerPage) {
        return (int) Math.ceil(infoDeskDao.countAllByStatusId(infoDeskStatusId)/rowsPerPage);
    }

    @Override
    public List<QuestionStatus> getStatusList() {
        return infoDeskDao.getStatusList();
    }

    @Override
    public QuestionStatus findStatusById(Long id) {
        return infoDeskDao.findStatusById(id);
    }

    @Override
    public InfoDesk findById(Long id) {
        return infoDeskDao.findById(id);
    }

    @Override
    public void save(InfoDesk infoDesk) {
        infoDeskDao.save(infoDesk);
    }

    @Override
    public void update(InfoDesk infoDesk) {
        InfoDesk infoDeskEdit = infoDeskDao.findById(infoDesk.getInfoDeskId());
        if (infoDeskEdit != null) {
            infoDeskEdit.setEmployeeId(infoDesk.getEmployeeId());
            infoDeskEdit.setInfoDeskQuestion(infoDesk.getInfoDeskQuestion());
            infoDeskEdit.setInfoDeskAnswer(infoDesk.getInfoDeskAnswer());
            infoDeskEdit.setInfoDeskStatusId(infoDesk.getInfoDeskStatusId());
        }
        infoDeskDao.update(infoDeskEdit);
    }

    @Override
    public void deleteById(Long id) {
        infoDeskDao.deleteById(id);
    }

    @Override
    public List<InfoDesk> findAll() {
        return infoDeskDao.findAll();
    }

    @Override
    public List<InfoDesk> getAllAsPage(int page, int rowsPerPage) {
        if(page > 1){
            page = (page - 1) * rowsPerPage + 1;
        }
        return infoDeskDao.getAllAsPage(page, rowsPerPage);
    }

    private int getPageNumber(int page, int rowsPerPage){
        if(page > 1){
            page = (page - 1) * rowsPerPage + 1;
        }

        return page;
    }

}
