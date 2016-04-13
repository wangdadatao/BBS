package com.datao.DAO;

import com.datao.entity.Favorites;
import com.datao.util.DBhelper;
import org.apache.commons.dbutils.handlers.BeanHandler;

/**
 * Created by 海涛 on 2016/4/13.
 * 收藏 DAO
 */
public class FavoritesDao {

    //添加收藏
    public void addFav(Favorites favorites) {
        String sql = "insert into favorites(userid,topicid) values(?,?)";
        DBhelper.updater(sql, favorites.getUserid(), favorites.getTopicid());
    }

    //删除收藏记录
    public void removeFav(Favorites favorite) {
        String sql = "DELETE FROM favorites where userid=? AND topicid=?";
        DBhelper.updater(sql, favorite.getUserid(), favorite.getTopicid());
    }

    //通过userID 和 topicID查找对象
    public Favorites findByUserIdandTopicId(Integer userid, Integer topicid) {
        String sql = "select * from favorites where userid=? and topicid=?";
        return DBhelper.query(sql,new BeanHandler<Favorites>(Favorites.class),userid,topicid);
    }
}
