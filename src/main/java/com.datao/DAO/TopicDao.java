package com.datao.DAO;

import com.datao.entity.Node;
import com.datao.entity.Topic;
import com.datao.entity.User;
import com.datao.util.DBhelper;
import com.google.common.collect.Lists;
import org.apache.commons.dbutils.BasicRowProcessor;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 海涛 on 2016/4/11.
 */
public class TopicDao {

    //添加新的帖子,并返回主键
    public Integer addOne(Topic topic) {
        String sql = "insert into topic (title,text,userid,nodeid,viewnum,favnum,likenum," +
                "replynum, createtime, replytime) values(?,?,?,?,?,?,?,?,?,?)";
        return DBhelper.insert(sql, topic.getTitle(), topic.getText(), topic.getUserid(),
                topic.getNodeid(), topic.getViewnum(), topic.getFavnum(), topic.getLikenum(), topic.getReplynum(),
                topic.getCreatetime(), topic.getReplytime()).intValue();
    }

    //根据id查找帖子
    public Topic findById(Integer topicId) {
        String sql = "SELECT topic.*,`user`.`username`, `user`.`headimg`,node.`type`FROM topic \n" +
                "INNER JOIN `user` ON topic.`userid` = `user`.`id`\n" +
                "INNER JOIN node ON topic.`nodeid` = node.`id`\n" +
                "WHERE topic.`id` = ?";
        return DBhelper.query(sql, new ResultSetHandler<Topic>() {
            @Override
            public Topic handle(ResultSet resultSet) throws SQLException {
                if (resultSet.next()) {
                    BasicRowProcessor rowProcessor = new BasicRowProcessor();

                    Topic topic = rowProcessor.toBean(resultSet, Topic.class);
                    User user = rowProcessor.toBean(resultSet, User.class);
                    Node node = rowProcessor.toBean(resultSet, Node.class);

                    topic.setUser(user);
                    topic.setNode(node);

                    return topic;
                }
                return null;
            }
        }, topicId);
    }

    //查询某一类的总条数
    public int cound(Integer nodeid) {
        String sql = "select count(*) from topic where nodeid = ?";
        return DBhelper.query(sql, new ScalarHandler<Long>(), nodeid).intValue();
    }

    //查询数据库中所有的条数
    public int cound() {
        String sql = "SELECT COUNT(*) from topic";
        return DBhelper.query(sql, new ScalarHandler<Long>()).intValue();
    }

    //查询规定数目的一页数据 规定了查询类型
    public List<Topic> queryOnePage(Integer code, int start, int size) {
        String sql = "SELECT topic.*,`user`.`username`,user.headimg from topic inner join user on topic.userid = user.id where nodeid = ? order by replytime desc limit ?,?";
        return DBhelper.query(sql, new FindOnePageHandler(), code, start, size);
    }

    //查询规定数目的一页数据  未规定查询类型
    public List<Topic> queryAPage(Integer start, Integer size) {
        String sql = "select topic.*, `user`.`username`, user.headimg from topic inner join user on topic.userid = user.id order by replytime desc limit ?,?";
        return DBhelper.query(sql, new FindOnePageHandler(), start, size);
    }

    //更新帖子内容
    public void update(Topic t) {
        String sql = "update topic set title=?, text=? , nodeid=?, viewnum=?, favnum=?, likenum=?, replynum=?, replytime=? where id=?";
        DBhelper.updater(sql, t.getTitle(), t.getText(), t.getNodeid(), t.getViewnum(), t.getFavnum(), t.getLikenum(), t.getReplynum(), t.getReplytime(), t.getId());
    }


    //自定义实现ResultHandler接口的类
    private class FindOnePageHandler implements ResultSetHandler<List<Topic>> {
        @Override
        public List<Topic> handle(ResultSet resultSet) throws SQLException {
            List<Topic> topics = Lists.newArrayList();
            BasicRowProcessor rowProcessor = new BasicRowProcessor();
            while (resultSet.next()) {
                Topic topic = rowProcessor.toBean(resultSet, Topic.class);
                User user = rowProcessor.toBean(resultSet, User.class);
                topic.setUser(user);
                topics.add(topic);
            }

            return topics;
        }
    }
}
