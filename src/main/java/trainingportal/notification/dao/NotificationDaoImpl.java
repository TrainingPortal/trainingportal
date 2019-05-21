package trainingportal.notification.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import trainingportal.notification.mapper.NotificationMapper;
import trainingportal.notification.model.Notification;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class NotificationDaoImpl extends JdbcDaoSupport implements NotificationDao {

    @Autowired
    public NotificationDaoImpl(DataSource dataSource) { this.setDataSource(dataSource); }

    @Override
    public List<Notification> findAllNotifications() {

        List<Notification> allNotifications = this.getJdbcTemplate().query(NotificationMapper.BASE_SQL,new NotificationMapper());
        return allNotifications;
    }

    @Override
    public List<Notification> findAllNotificationsOfUsers() {

        String sql = NotificationMapper.USERS_LEFT_JOIN_NOTIFICATION + "WHERE enabled = 1 AND role_id = 2";
        List<Notification> allUsersNotifications = this.getJdbcTemplate().query(sql, new NotificationMapper());
        return allUsersNotifications;
    }

    @Override
    public List<Notification> findAllNotificationsOfTrainers() {

        String sql = NotificationMapper.USERS_LEFT_JOIN_NOTIFICATION + "WHERE enabled = 1 AND role_id = 3";
        List<Notification> allTrainersNotifications = this.getJdbcTemplate().query(sql, new NotificationMapper());
        return allTrainersNotifications;
    }

    @Override
    public List<Notification> findAllNotificationsOfManagers() {

        String sql = NotificationMapper.USERS_LEFT_JOIN_NOTIFICATION + "WHERE enabled = 1 AND role_id = 4";
        List<Notification> allManagersNotifications = this.getJdbcTemplate().query(sql, new NotificationMapper());
        return allManagersNotifications;
    }

    @Override
    public Notification findNotificationByID(Long notificationID) {

        String sql = NotificationMapper.BASE_SQL + "WHERE notification.id = ?";

        Notification notification = this.getJdbcTemplate().queryForObject(sql,new Object[]{notificationID},new NotificationMapper());
        return notification;
    }

    @Override
    public Boolean isNotificationExist(Notification notificationID) {

        return findNotificationByID(notificationID.getNotificationID())==null ? false:true ;
    }

    @Override
    public void setNotificationMessage(Notification notificationID, String notificationMessage) {

        String sql = NotificationMapper.UPDATE_SQL_MESSAGE + "WHERE notification.id = ?";
        this.getJdbcTemplate().update(sql,notificationMessage,notificationID.getNotificationID());
    }
}
