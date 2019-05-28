package trainingportal.notification.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import trainingportal.notification.mapper.NotificationMapper;
import trainingportal.notification.model.Notification;

import javax.sql.DataSource;
import java.text.SimpleDateFormat;
import java.util.List;

@Repository
public class NotificationDaoImpl extends JdbcDaoSupport implements NotificationDao {

    @Autowired
    public NotificationDaoImpl(DataSource dataSource) { this.setDataSource(dataSource); }

    @Override
    public List<Notification> findAllNotifications() {

        return this.getJdbcTemplate().query(NotificationMapper.BASE_SQL,new NotificationMapper());
    }

    @Override
    public List<Notification> findAllNotificationsOfUsers() {

        String sql = NotificationMapper.USERS_LEFT_JOIN_NOTIFICATION + NotificationMapper.WHERE_ENABLED_AND_ROLE + 2;
        return this.getJdbcTemplate().query(sql, new NotificationMapper());
    }

    @Override
    public List<Notification> findAllNotificationsOfTrainers() {

        String sql = NotificationMapper.USERS_LEFT_JOIN_NOTIFICATION + NotificationMapper.WHERE_ENABLED_AND_ROLE + 3;
        return this.getJdbcTemplate().query(sql, new NotificationMapper());
    }

    @Override
    public List<Notification> findAllNotificationsOfManagers() {

        String sql = NotificationMapper.USERS_LEFT_JOIN_NOTIFICATION + NotificationMapper.WHERE_ENABLED_AND_ROLE + 4;
        return this.getJdbcTemplate().query(sql, new NotificationMapper());
    }

    @Override
    public Notification findNotificationByID(Long notificationID) {

        String sql = NotificationMapper.BASE_SQL + NotificationMapper.WHERE_ID;
        return this.getJdbcTemplate().queryForObject(sql,new Object[]{notificationID},new NotificationMapper());
    }

    @Override
    public Boolean isNotificationExist(Notification notificationID) {

        return findNotificationByID(notificationID.getNotificationID()) != null;
    }

    @Override
    public void setNotificationMessage(Notification notificationID, String notificationMessage) {

        String sql = NotificationMapper.UPDATE_SQL_MESSAGE + NotificationMapper.WHERE_ID;
        this.getJdbcTemplate().update(sql,notificationMessage,notificationID.getNotificationID());
    }

    @Override
    public void saveNewNotification(Notification notification) {

        String date = new SimpleDateFormat("yyyy-MM-dd").format(notification.getNotificationDate());
        this.getJdbcTemplate().update(NotificationMapper.CREATE_NEW_NOTIFICATION_SQL,notification.getNotificationMessage(),date,notification.getNotificationUserID(),notification.getNotificationStatusID());
    }
}
