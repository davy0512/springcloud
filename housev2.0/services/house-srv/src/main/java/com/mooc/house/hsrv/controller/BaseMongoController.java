package com.mooc.house.hsrv.controller;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mooc.house.hsrv.utils.MongoDBTemplate;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author liwei
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}
 * @date 2018/5/711:24
 */
@Component("BaseMongodb")
public class BaseMongoController{
        private static Log logger = LogFactory.getLog(BaseMongoController.class);
        //商户用户
        static String COLLECTION_NAME = "ORG_USER";

        @Autowired
        MongoDBTemplate mgTemplate;

        //新增用户  返回 用户名和密码
        public int addUser(String orgCode, String mobileNum, String IDCard, String contact, String userId, String pwd) {
            //构建文档（本身是面向文档存储）
            DBObject document = new BasicDBObject();
            document.put("orgCode", orgCode);
            document.put("mobileNum", mobileNum);
            document.put("IDCard", IDCard);
            document.put("contact", contact);
            document.put("userId", userId);
            document.put("pwd", pwd);
            //设置为使用中
            document.put("status", 1);
            //存入的时候，注意相同的数据只保存一条
            document.put("ID", orgCode);

            //获取集合，在集合中插入数据 getN()返回受影响的条数
            int row = mgTemplate.getCollection(COLLECTION_NAME).save(document).getN();
            return row;
        }

        //查询用户(找回用户信息)
        public DBObject queryUser(String userId, String orgCode, String mobileNum, String idCard) {
            DBObject query = buildQuery(userId, orgCode, mobileNum, idCard);
            DBObject dbObject = new BasicDBObject();
            dbObject.put("ID", 0);
            DBObject result = mgTemplate.getCollection(COLLECTION_NAME).findOne(query, dbObject);
            return result;
        }

        //构造查询条件
        private DBObject buildQuery(String userId, String orgCode, String mobileNum, String idCard) {
            DBObject query = new BasicDBObject();
            if (StringUtils.isNotBlank(userId)) {
                query.put("userId", userId);
            }
            if (StringUtils.isNotBlank(orgCode)) {
                query.put("orgCode", orgCode);
            }
            if (StringUtils.isNotBlank(mobileNum)) {
                query.put("mobileNum", mobileNum);
            }
            if (StringUtils.isNotBlank(idCard)) {
                query.put("IDCard", idCard);
            }
            return query;
        }
}
