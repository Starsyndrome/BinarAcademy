package org.binaracademy.challenge4.service;

import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.binaracademy.challenge4.model.response.OrderDetail;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
@Slf4j
public class InvoiceServiceImplements implements InvoiceService{
    /*
    Maaf ya kak literally copy-paste, puyeng bat asli sama jasper report ini ya Allah
     */
    @Override
    public byte[] generateInvoice(String username) throws FileNotFoundException, JRException {
        log.info("Generating invoice for:  {}", username);
        List<OrderDetail> orderDetails = Arrays.asList(
                OrderDetail.builder().productName("Cappuccino").price("Rp. 20.000").quantity(2L).build(),
                OrderDetail.builder().productName("Croissant").price("Rp. 17.000").quantity(1L).build(),
                OrderDetail.builder().productName("Red Velvet").price("Rp. 20.000").quantity(1L).build()
        );

        Map<String, Object> parameterMap = new HashMap<>();
        parameterMap.put("username", username);
        parameterMap.put("finalPrice", "Rp. 77.000");
        parameterMap.put("orderDetail", orderDetails);
        JasperPrint invoice = JasperFillManager.fillReport(
                JasperCompileManager.compileReport(ResourceUtils.getFile("classpath:invoice_v2.jrxml").getAbsolutePath()),
                parameterMap,
                new JRBeanCollectionDataSource(orderDetails)
        );

        return JasperExportManager.exportReportToPdf(invoice);
    }
}
