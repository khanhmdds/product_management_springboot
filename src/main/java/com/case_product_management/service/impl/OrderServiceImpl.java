package com.case_product_management.service.impl;

import com.case_product_management.dto.SearchOrderObject;
import com.case_product_management.model.Order;
import com.case_product_management.model.User;
import com.case_product_management.repository.IOrderRepository;
import com.case_product_management.service.IOrderService;
import com.fasterxml.jackson.databind.util.ArrayBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import com.querydsl.core.BooleanBuilder;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@Service
public class OrderServiceImpl implements IOrderService {
    @Autowired
    private IOrderRepository orderRepository;

//    @Override
//    public Page<Order> getAllOrderByFilter(SearchOrderObject object, int page)
//        throws ParseException {
//        BooleanBuilder builder = new ArrayBuilders.BooleanBuilder();
//
//        String orderStatus = object.getOrderStatus();
//        String fromDay = object.getFromDay();
//        String toDay = object.getToDay();
//        SimpleDateFormat formatDate = new SimpleDateFormat("dd-MM-yyyy");
//
//        if (!orderStatus.equals("")) {
//            builder.and(QDonHang.donHang.trangThaiDonHang.eq(orderStatus));
//        }
//
//        if (!tuNgay.equals("") && tuNgay != null) {
//            if (trangThaiDon.equals("") || trangThaiDon.equals("Đang chờ giao") || trangThaiDon.equals("Đã hủy")) {
//                builder.and(QDonHang.donHang.ngayDatHang.goe(formatDate.parse(tuNgay)));
//            } else if (trangThaiDon.equals("Đang giao")) {
//                builder.and(QDonHang.donHang.ngayGiaoHang.goe(formatDate.parse(tuNgay)));
//            } else { // hoàn thành
//                builder.and(QDonHang.donHang.ngayNhanHang.goe(formatDate.parse(tuNgay)));
//            }
//        }
//
//        if (!denNgay.equals("") && denNgay != null) {
//            if (trangThaiDon.equals("") || trangThaiDon.equals("Đang chờ giao") || trangThaiDon.equals("Đã hủy")) {
//                builder.and(QDonHang.donHang.ngayDatHang.loe(formatDate.parse(denNgay)));
//            } else if (trangThaiDon.equals("Đang giao")) {
//                builder.and(QDonHang.donHang.ngayGiaoHang.loe(formatDate.parse(denNgay)));
//            } else { // hoàn thành
//                builder.and(QDonHang.donHang.ngayNhanHang.loe(formatDate.parse(denNgay)));
//            }
//        }
//
//        return donHangRepo.findAll(builder, PageRequest.of(page - 1, 6));
//    }

    @Override
    public Order update(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Order findById(long id) {
        return orderRepository.findById(id).get();
    }

    @Override
    public Order save(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public List<Object> takeOrderByMonthAndYear() {
        return orderRepository.takeOrderByMonthAndYear();
    }

    @Override
    public List<Order> getOrderByUser(User user) {
        return orderRepository.findByUser(user);
    }

    @Override
    public int countByOrderStatus(String orderStatus) {
        return orderRepository.countByOrderStatus(orderStatus);
    }
}
