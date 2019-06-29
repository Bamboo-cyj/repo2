package com.fengk.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.fengk.dao.HealthOrderSettingDao;
import com.fengk.dao.MemberDao;
import com.fengk.dao.OrderDao;
import com.fengk.pojo.Member;
import com.fengk.pojo.Order;
import com.fengk.pojo.OrderSetting;
import com.fengk.redis.RedisJob;
import com.fengk.service.MobileOrderService;
import com.fengk.utils.MessageConstant;
import com.fengk.utils.RedisMessageConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 */
@Service(interfaceClass = MobileOrderService.class)
@Transactional
public class MobileOrderServiceImpl implements MobileOrderService {
    @Autowired
    OrderDao orderDao;
    @Autowired
    MemberDao memberDao;
    @Autowired
    RedisJob redisJob;
    @Autowired
    HealthOrderSettingDao healthOrderSettingDao;

    @Override
    public Order submit(Map orderInfo) {
        String telphone = (String) orderInfo.get("telephone");
        String code = redisJob.getCodeFromRedis(telphone, RedisMessageConstant.SENDTYPE_ORDER);
        if (code == null) {
            throw new RuntimeException("请先获取手机验证码再提交");
        }
        if (!code.equals(orderInfo.get("validateCode"))) {
            throw new RuntimeException(MessageConstant.VALIDATECODE_ERROR);

        }
        String oderDate = (String) orderInfo.get("orderDate");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date dateFormat = null;
        try {
            dateFormat = simpleDateFormat.parse(oderDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long length = healthOrderSettingDao.findLength(oderDate);
        if (length == 0) {
            throw new RuntimeException(MessageConstant.SELECTED_DATE_CANNOT_ORDER);
        }
        OrderSetting orderSetting = healthOrderSettingDao.findOrderSettingByDate(oderDate);
        if (orderSetting.getReservations() == orderSetting.getNumber()) {
            throw new RuntimeException(MessageConstant.ORDER_FULL);
        }
        Member member = memberDao.findByTelephone(telphone);


        if (member != null) {
            Order order = new Order();
            order.setMemberId(member.getId());
            order.setOrderDate(dateFormat);
            order.setSetmealId(Integer.parseInt(orderInfo.get("setmealId").toString()));
            List<Order> orderList = orderDao.findByCondition(order);
            if (orderList != null && orderList.size() > 0) {
                throw new RuntimeException(MessageConstant.HAS_ORDERED);
            }

        } else {
            member = new Member();
            member.setPhoneNumber(telphone);
            member.setSex(orderInfo.get("sex").toString());
            member.setName(orderInfo.get("name").toString());
            member.setIdCard(orderInfo.get("idCard").toString());

            member.setRegTime(new Date());
            memberDao.add(member);
            System.out.println(member);

        }


        synchronized (MobileOrderService.class) {
            Order newOrder = new Order();

            newOrder.setMemberId(member.getId());
            newOrder.setOrderDate(dateFormat);
            newOrder.setOrderType(Order.ORDERTYPE_WEIXIN);
            newOrder.setOrderStatus(Order.ORDERSTATUS_NO);
            newOrder.setSetmealId(Integer.parseInt(orderInfo.get("setmealId").toString()));
            orderDao.add(newOrder);
            healthOrderSettingDao.addCount(oderDate);
            return newOrder;
        }


    }

    @Override
    public Map findById(Integer id) {
        Map byId4Detail = orderDao.findById4Detail(id);
        Date orderDate = (Date) byId4Detail.get("orderDate");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String orderDateFormat = simpleDateFormat.format(orderDate);
        byId4Detail.put("orderDate", orderDateFormat);
        return byId4Detail;
    }
}
