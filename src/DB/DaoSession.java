package DB;

import android.database.sqlite.SQLiteDatabase;

import java.util.Map;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.AbstractDaoSession;
import de.greenrobot.dao.identityscope.IdentityScopeType;
import de.greenrobot.dao.internal.DaoConfig;

import DB.fund;

import DB.fundDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see de.greenrobot.dao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig fundDaoConfig;

    private final fundDao fundDao;

    public DaoSession(SQLiteDatabase db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        fundDaoConfig = daoConfigMap.get(fundDao.class).clone();
        fundDaoConfig.initIdentityScope(type);

        fundDao = new fundDao(fundDaoConfig, this);

        registerDao(fund.class, fundDao);
    }
    
    public void clear() {
        fundDaoConfig.getIdentityScope().clear();
    }

    public fundDao getFundDao() {
        return fundDao;
    }

}
