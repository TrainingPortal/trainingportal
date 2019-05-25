package trainingportal.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import trainingportal.dao.InfoDeskDao;
import trainingportal.model.InfoDesk;

import java.util.List;

@Service("infoDeskServiceImpl")
@Transactional
public class InfoDeskServiceImpl implements InfoDeskService {

    @Autowired
    InfoDeskDao infoDeskDao;

    @Override
    public InfoDesk findById(Long id) {
        return null;
    }

    @Override
    public void save(InfoDesk infoDesk) {
        infoDeskDao.save(infoDesk);
    }

    @Override
    public void update(InfoDesk infoDesk) {

    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public List<InfoDesk> findAll() {
        return null;
    }

    @Override
    public List<InfoDesk> getAllAsPage(int page, int total) {
        if(page == 1){
            //do nothing
        } else {
            page = (page - 1) * total + 1;
        }

        List<InfoDesk> infoDeskList = infoDeskDao.getAllAsPage(page, total);

        return infoDeskList;
    }

    @Override
    public List<InfoDesk> getRequestForHelpByEmpId(Long employeeId) {
      return infoDeskDao.getRequestForHelpByEmpId(employeeId);
    }

    @Override
    public int getPages(double total) {
        return (int) Math.ceil(infoDeskDao.countAll() / total);
    }
}
