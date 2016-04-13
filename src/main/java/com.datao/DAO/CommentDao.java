package com.datao.DAO;

import com.datao.entity.Comment;
import com.datao.entity.User;
import com.datao.util.DBhelper;
import com.google.common.collect.Lists;
import org.apache.commons.dbutils.BasicRowProcessor;
import org.apache.commons.dbutils.ResultSetHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by 海涛 on 2016/4/12.
 * CommentDao
 */
public class CommentDao {

    //查询 某个帖子下的所有的comment
    public List<Comment> findByTopicId(Integer topicId) {
        String sql = "select `comment`.*, user.username, user.headimg from `comment` " +
                "inner join user on `comment`.userid = user.id " +
                "WHERE topicid = ?";
        return DBhelper.query(sql, new ResultSetHandler<List<Comment>>() {
            @Override
            public List<Comment> handle(ResultSet resultSet) throws SQLException {
                List<Comment> comments = Lists.newArrayList();
                BasicRowProcessor basicRowProcessor = new BasicRowProcessor();
                while (resultSet.next()) {
                    Comment comment = basicRowProcessor.toBean(resultSet, Comment.class);
                    User user = basicRowProcessor.toBean(resultSet, User.class);
                    comment.setUser(user);
                    comments.add(comment);
                }
                return comments;
            }
        }, topicId);
    }


    //保存新的评论
    public void addComment(Comment comment) {
        String sql = "insert into comment(userid,topicid,texts,createtime) values(?,?,?,?)";
        DBhelper.updater(sql, comment.getUserid(), comment.getTopicid(), comment.getTexts(), comment.getCreatetime());
    }
}
