package services;

import java.io.IOException;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;

import models.tables.ActivityLog;
import models.tables.User;
import play.cache.CacheApi;
import play.db.jpa.Transactional;
import play.mvc.Controller;

/**
 *The type ActivityLog service
 */
@Singleton
public class ActivityLogService extends BaseService {
	
	/**
	 * The Cache.
	 */
	CacheApi cache;
	
	@Inject
	public ActivityLogService() { }
	
	/**
	 * Gets all activity logs.
	 * 
	 * @return the all activity logs
	 * 
	 */
	@SuppressWarnings("unchecked")
	public List<ActivityLog> getAllActivityLogs() {
		return (List<ActivityLog>) getSession().createCriteria(ActivityLog.class).list();
	}

	/**
	 * Post activity log.
	 *
	 * @param activity the activity
	 * @return the boolean
	 */
	@Transactional
	public Boolean postActivityLog(String activity) throws IOException{
		//User user = cache.get(Controller.session("uid"));
		ActivityLog activity_log = new ActivityLog();
		//activity_log.setUser(user);
		activity_log.setActivity(activity.toString());
		activity_log.setDate_time(System.currentTimeMillis());
		getSession().save(activity_log);
		return true;
	}
}