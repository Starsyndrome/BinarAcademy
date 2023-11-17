package org.binaracademy.challenge4.service.implement;

import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.binaracademy.challenge4.DTO.response.OrderDetailJasper;
import org.binaracademy.challenge4.model.Users;
import org.binaracademy.challenge4.repository.OrderDetailRepository;
import org.binaracademy.challenge4.repository.UserRepository;
import org.binaracademy.challenge4.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;

import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;


@Service
@Slf4j
public class InvoiceServiceImplements implements InvoiceService {

    @Autowired
    OrderDetailRepository orderDetailRepository;

    @Autowired
    UserRepository userRepository;

    @Transactional(readOnly = true)
    @Override
    public byte[] generateInvoice(String username) throws FileNotFoundException, JRException {
        log.info("Generating invoice for: {}", username);
        Users users = userRepository.getByUsername(username);

        List<OrderDetailJasper> orderDetailJaspers = orderDetailRepository.findAll().stream()
                .map(orderDetails -> OrderDetailJasper.builder()
                        .productName(orderDetails.getProduct().getProductName())
                        .quantity(Long.valueOf(orderDetails.getQty()))
                        .price(String.valueOf(orderDetails.getProduct().getPrice()))
                        .build())
                .collect(Collectors.toList());

        Map<String, Object> parameterMap = new HashMap<>();
        parameterMap.put("username", users.getUsername());
        parameterMap.put("finalPrice", "Rp. 100.000");
        parameterMap.put("orderDetailJasper", orderDetailJaspers);
        JasperPrint invoice = JasperFillManager.fillReport(
                JasperCompileManager.compileReport(ResourceUtils.getFile("classpath:invoice_v2.jrxml")
                        .getAbsolutePath()), parameterMap, new JRBeanCollectionDataSource(orderDetailJaspers)
        );
        return JasperExportManager.exportReportToPdf(invoice);
    }
}