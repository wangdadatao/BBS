package com.datao.service.topic;

import com.datao.DAO.CommentDao;
import com.datao.DAO.FavoritesDao;
import com.datao.DAO.NodeDao;
import com.datao.DAO.TopicDao;
import com.datao.entity.*;
import com.datao.exception.DataAccessException;
import com.datao.util.Page;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;

import java.util.List;

/**
 * Created by 海涛 on 2016/4/11.
 * topic 服务类
 */
public class TopicService {

    private NodeDao nodeDao = new NodeDao();
    private TopicDao topicDao = new TopicDao();
    private CommentDao commentDao = new CommentDao();
    private FavoritesDao favoritesDao = new FavoritesDao();


    //查询所有的的主题
    public List<Node> findNodes() {
        return nodeDao.findAll();
    }

    //把新建的帖子添加到数据库
    public Integer addTopic(String title, String text, String nodeid, User user) {
        String now = DateTime.now().toString("yyyy-MM-dd HH:mm:ss");

        Topic topic = new Topic();
        topic.setTitle(title);
        topic.setText(text);
        topic.setUserid(user.getId());
        topic.setNodeid(Integer.valueOf(nodeid));
        topic.setViewnum(0);
        topic.setFavnum(0);
        topic.setLikenum(0);
        topic.setReplynum(0);
        topic.setCreatetime(now);
        topic.setReplytime(now);

        return topicDao.addOne(topic);
    }

    //根据帖子ID查找内容
    public Topic findById(String code) {
        Topic topic = topicDao.findById(Integer.valueOf(code));
        if (topic != null) {
            return topic;
        } else {
            throw new DataAccessException("查询的帖子不存在或已被删除!");
        }
    }

    //
    public Page<Topic> showIndexTopic(String code, String pageNo) {
        int pageSize = 20;
        Page<Topic> page = null;

        if (StringUtils.isNumeric(code)) {
            int count = topicDao.cound(Integer.valueOf(code));
            page = new Page(pageNo, count, pageSize);
            List<Topic> topics = new TopicDao().queryOnePage(Integer.valueOf(code), page.getStart(), page.getSize());
            page.setItems(topics);
        } else {
            int count = topicDao.cound();
            page = new Page(pageNo, count, pageSize);
            List<Topic> topics = new TopicDao().queryAPage(page.getStart(), page.getSize());
            page.setItems(topics);
        }

        return page;

    }

    //查询某个帖子下的所有评论
    public List<Comment> findComment(String topicId) {
        return commentDao.findByTopicId(Integer.valueOf(topicId));
    }

    //储存评论
    public void saveComment(User user, String commentText, Topic topic) {
        String nowTime = DateTime.now().toString("yyyy-MM-dd HH:mm:ss");
        Comment comment = new Comment();
        comment.setUserid(user.getId());
        comment.setTopicid(topic.getId());
        comment.setCreatetime(nowTime);
        comment.setTexts(commentText);

        topic.setReplynum(topic.getReplynum() + 1);
        topic.setReplytime(nowTime);
        topicDao.update(topic);
        commentDao.addComment(comment);
    }

    //添加帖子的浏览量
    public void addView(Topic topic) {
        topic.setViewnum(topic.getViewnum() + 1);
        topicDao.update(topic);
    }

    //添加收藏
    public void addFav(User user, Topic topic, String favStatus) {
        Favorites favorite = new Favorites();
        if (user != null) {
            favorite.setUserid(user.getId());
            favorite.setTopicid(topic.getId());

            Favorites fa = favoritesDao.findByUserIdandTopicId(user.getId(), topic.getId());

            if (favStatus.equals("fav")) {
                if (fa == null) {
                    favoritesDao.addFav(favorite);
                    topic.setFavnum(topic.getFavnum() + 1);
                    topicDao.update(topic);
                }
            } else {
                favoritesDao.removeFav(favorite);

                topic.setFavnum(topic.getFavnum() - 1);
                topicDao.update(topic);
            }

        } else {
            throw new DataAccessException("参数错误!");
        }
    }

    //查找是否已经收藏
    public Favorites findFav(User user, Topic topic) {
            return favoritesDao.findByUserIdandTopicId(user.getId(), topic.getId());
    }
}
